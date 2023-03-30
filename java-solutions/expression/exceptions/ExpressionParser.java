package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringCharSource;

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
            final Operand result = parseBitOperation();
            if (eof()) {
                return result;
            }
            throw new EOFException(errorChar());
        }

        private Operand parseBitOperation() throws ParseException {
            Operand leftOperand = parseAdd();
            while (true) {
                skipWhitespace();
                final String identifier = parseIdentifier();
                if ("set".equals(identifier)) {
                    leftOperand = new CheckedSet(leftOperand, parseAdd());
                } else if ("clear".equals(identifier)) {
                    leftOperand = new CheckedClear(leftOperand, parseAdd());
                } else if (identifier.isEmpty()){
                    return leftOperand;
                } else {
                    skipWhitespace();
                    throw new BinaryOperationException(identifier, source.getPos());
                }
            }
        }

        private Operand parseAdd() throws ParseException {
            Operand leftOperand = parseMult();
            while (true) {
                skipWhitespace();
                if (take('+')) {
                    leftOperand = new CheckedAdd(leftOperand, parseMult());
                } else if (take('-')) {
                    leftOperand = new CheckedSubtract(leftOperand, parseMult());
                } else {
                    return leftOperand;
                }
            }
        }

        private Operand parseMult() throws ParseException {
            Operand leftOperand = parsePrim();
            while (true) {
                skipWhitespace();
                if (take('*')) {
                    leftOperand = new CheckedMultiply(leftOperand, parsePrim());
                } else if (take('/')) {
                    leftOperand = new CheckedDivide(leftOperand, parsePrim());
                } else {
                    return leftOperand;
                }
            }
        }

        private Operand parsePrim() throws ParseException {
            skipWhitespace();
            if (take('(')) {
                final Operand operand = parseBitOperation();
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
            } else if (take('x')) {
                return new Variable("x");
            } else if (take('y')) {
                return new Variable("y");
            } else if (take('z')) {
                return new Variable("z");
            } else {
                final String identifier = parseIdentifier();
                if ("count".equals(identifier)) {
                    return new CheckedCount(parsePrim());
                } else if ("pow10".equals(identifier)) {
                    return new CheckedPow(parsePrim());
                } else if ("log10".equals(identifier)) {
                    return new CheckedLog(parsePrim());
                } else {
                    throw new PrimExpectedException(identifier.isEmpty() ? errorChar() : identifier, source.getPos());
                }
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
        System.out.println(new ExpressionParser().parse("xset y"));
    }
}
