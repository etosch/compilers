package circuit.compiler.lexer;
public class Word extends Token{
    public String lexeme = "";
    public Word(Stirng s, int tag) { super(tag); lexeme=s;}
    public static final Word
	assign = new Word("<-", Tag.ASSIGN)
	name = new Word("name", Tag.NAME)
	input = new Word("input:", Tag.INPUT)
	output = new Word("output:", Tag.OUTPUT);
}