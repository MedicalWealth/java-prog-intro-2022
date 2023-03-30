package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringCharSource;

import java.util.List;

public final class ExpressionParser implements TripleParser  {
    public ExpressionParser() {
    }

    @Override
    public TripleExpression parse(final String expression) throws ParseException {
        return new Parser(new StringCharSource(expression)).parseExpression();
    }

    private static final class Parser extends BaseParser {
        public Parser(final CharSource source) {
            super(source);
        }

        private Operand parseExpression() throws ParseException {
            final Operand result = parseOperation(0);
            if (eof()) {
                return result;
            }
            throw new EOFException(errorChar(), source.getPos());
        }

        private final List<List<String>> operations = List.of(
            List.of("set", "clear"),
            List.of("+", "-"),
            List.of("*", "/")
        );

        private Operand parseOperation(int priority) throws ParseException {
            Operand leftOperand = parseNext(priority + 1);
            while (true) {
                skipWhitespace();
                final List<String> currentOperations = operations.get(priority);
                boolean isValidOperation = false;
                for (String operation: currentOperations) {
                    if (take(operation)) {
                        leftOperand = parseOperation(priority, leftOperand, operation);
                        isValidOperation = true;
                    }
                }
                if (!isValidOperation) {
                    return leftOperand;
                }
            }
        }

        private Operand parseOperation(int priority, Operand leftOperand, String operation) throws ParseException {
            switch (operation) {
                case "+" -> leftOperand = new CheckedAdd(leftOperand, parseNext(priority + 1));
                case "-" -> leftOperand = new CheckedSubtract(leftOperand, parseNext(priority + 1));
                case "*" -> leftOperand = new CheckedMultiply(leftOperand, parseNext(priority + 1));
                case "/" -> leftOperand = new CheckedDivide(leftOperand, parseNext(priority + 1));
                case "set" -> {
                    checkCorrectIdentifier();
                    leftOperand = new CheckedSet(leftOperand, parseNext(priority + 1));
                }
                case "clear" -> {
                    checkCorrectIdentifier();
                    leftOperand = new CheckedClear(leftOperand, parseNext(priority + 1));
                }
            }
            return leftOperand;
        }

        private void checkCorrectIdentifier() throws BinaryOperationException {
            if (!Character.isWhitespace(ch) && (ch != '-') && (ch != '(')) {
                throw new BinaryOperationException(String.valueOf(ch), source.getPos());
            }
        }

        private Operand parseNext(int priority) throws ParseException {
            if (priority == operations.size()) {
                return parsePrim();
            } else {
                return parseOperation(priority);
            }
        }

        private Operand parsePrim() throws ParseException {
            skipWhitespace();
            if (take('(')) {
                final Operand operand = parseOperation(0);
                if (!take(')')) {
                    throw new NoBracketException(')', errorChar(), source.getPos());
                }
                return operand;
            }  else if (take('-')) {
                if (Character.isDigit(ch)) {
                    return new Const(parseConstant(true));
                } else {
                    return new CheckedNegate(parsePrim());
                }
            } else if (Character.isDigit(ch)) {
                return new Const(parseConstant(false));
            } else {
                final String identifier = parseIdentifier();
                return switch (identifier) {
                    case "count" -> new CheckedCount(parsePrim());
                    case "pow10" -> new CheckedPow(parsePrim());
                    case "log10" -> new CheckedLog(parsePrim());
                    case "x" -> new Variable("x");
                    case "y" -> new Variable("y");
                    case "z" -> new Variable("z");
                    default ->
                        throw new PrimExpectedException(identifier.isEmpty() ? errorChar() : identifier, source.getPos());
                };
            }
        }

        private int parseConstant(final boolean isNegative) {
            final StringBuilder sb = new StringBuilder(isNegative ? "-" : "");
            while (Character.isDigit(ch)) {
                sb.append(take());
            }
            try {
                return Integer.parseInt(sb.toString());
            } catch (final NumberFormatException e) {
                throw new OverflowException(sb.toString());
            }
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(ch) && !eof()) {
                take();
            }
        }

        private String parseIdentifier() {
            skipWhitespace();
            final StringBuilder result = new StringBuilder();
            while (Character.isJavaIdentifierPart(ch) && !eof()) {
                result.append(take());
            }
            return result.toString();
        }
    }

    public static void main(final String... args) throws ParseException {
        System.out.println(new ExpressionParser().parse("x + y + z"));
    }
}