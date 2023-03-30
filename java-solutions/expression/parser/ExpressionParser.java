package expression.parser;

import expression.*;

public final class ExpressionParser implements TripleParser  {
    public ExpressionParser() {
    }

    @Override
    public TripleExpression parse(final String expression) {
        return new Parser(new StringCharSource(expression)).parseExpression();
    }

    private static final class Parser extends BaseParser {

        public static final String NO_OPERATION = "";

        public Parser(final CharSource source) {
            super(source);
        }

        private Operand parseExpression() {
            final Operand result = parseBitOperation();
            if (eof()) {
                return result;
            }
            throw error("End of expression expected");
        }

        // :NOTE: 2 setx
        private Operand parseBitOperation() {
            Operand leftOperand = parseAdd();
            while (true) {
                skipWhitespace();
                final String identifier = parseIdentifier();
                if ("set".equals(identifier)) {
                    leftOperand = new Set(leftOperand, parseAdd());
                } else if ("clear".equals(identifier)) {
                    leftOperand = new Clear(leftOperand, parseAdd());
                } else {
                    return leftOperand;
                }
            }
        }

        private Operand parseAdd() {
            skipWhitespace();
            Operand leftOperand = parseMult();
            while (true) {
                skipWhitespace();
                if (take('+')) {
                    leftOperand = new Add(leftOperand, parseMult());
                } else if (take('-')) {
                    leftOperand = new Subtract(leftOperand, parseMult());
                } else {
                    return leftOperand;
                }
            }
        }

        private Operand parseMult() {
            skipWhitespace();
            Operand leftOperand = parsePrim();
            while (true) {
                skipWhitespace();
                if (take('*')) {
                    leftOperand = new Multiply(leftOperand, parsePrim());
                } else if (take('/')) {
                    leftOperand = new Divide(leftOperand, parsePrim());
                } else {
                    return leftOperand;
                }
            }
        }

        private Operand parsePrim() {
            skipWhitespace();
            if (take('(')) {
                final Operand operand = parseBitOperation();
                expect(')');
                return operand;
            }  else if (take('-')) {
                if (Character.isDigit(ch)) {
                    return new Const(parseConstant(true));
                } else {
                    return new Negate(parsePrim());
                }
            } else if (Character.isDigit(ch)) {
                return new Const(parseConstant(false));
            } else {
                final String identifier = parseIdentifier();
                if ("count".equals(identifier)) {
                    return new Count(parsePrim());
                } else {
                    return new Variable(identifier);
                }
            }
        }

        private int parseConstant(final boolean isNegative) {
            final StringBuilder sb = new StringBuilder(isNegative ? "-" : "");
            while (Character.isDigit(ch)) {
                sb.append(take());
            }
            return Integer.parseInt(sb.toString());
        }

        private String parseIdentifier() {
            if (!Character.isJavaIdentifierStart(ch) || eof()) {
                return NO_OPERATION;
            }
            final StringBuilder result = new StringBuilder();
            while (Character.isJavaIdentifierPart(ch) && !eof()) {
                result.append(take());
            }
            return result.toString();
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(ch) && !eof()) {
                take();
            }
        }
    }
}
