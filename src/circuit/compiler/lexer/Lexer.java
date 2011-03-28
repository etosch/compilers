package circuit.compiler.lexer;

public class Lexer{
    public char peek = ' ';
    public Hashtable words = new Hashtable();
    public String textToAnalyse;
    public void reserve(Word w){ words.put(w.lexeme, w);}

    public Lexer(){
	reserve(Word.assign);
	reserve(Word.input);
	reserve(Word.output);
	reserve(Word.name);
    }

    public void setTextToAnalyse(String textToAnalyse){
	this.textToAnalyse=textToAnalyse;
    }
    
    public void readch(){
	//	peek  =
    }
}