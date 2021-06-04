/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compiler_project;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author dell
 */
public class SCANNER {

    /**
     * @param args the command line arguments
     */
    private int line;
    private int pos;
    private int position;
    private char chr;
    private final String s;
    private final String match;
    public int en=0;
    public ArrayList<Integer> li = new ArrayList<Integer>();

   Editor e = new Editor();
    
    Map<String, TokenType> keywords = new HashMap<>();

    static class Token 
    {
        public TokenType tokentype;
        public String value;
        public int line;
        public int pos;
        public String match;
        Token(TokenType token, String value, int line, int pos,String match) 
        {
            this.tokentype = token; this.value = value; this.line = line; this.pos = pos;   this.match = match;
        }
        @Override
        public String toString() //Lexeme
        {
            String result ="";
            switch (this.tokentype) 
            {
                case Constant:
                    result += str();
                    break;
                case Identifier:
                    result += str();
                    break;
                case String:
                    result +=str();
                    break;
                case Integer:
                    result +=str();
                    break;                 
                case Arithmetic_Operation:
                    result +=str();
                    break;
                case SInteger:
                    result +=str();
                    break;
                case Character:
                    result += str();
                    break;
                case Float:
                    result += str();
                    break;
                case SFloat:
                    result += str();
                    break;
                case Loop:
                    result +=str();
                    break;
                case Void:
                    result +=str();
                    break; 
                case Return:
                    result +=str();
                    break;
                case Break:
                    result +=str();
                    break;
                case Assignment_operator:
                    result += str();
                    break;
                case Quotation_Mark:
                    result += str();
                    break;
                case commment:
                    result += str();
                    break;
                case Token_Delimiter:
                    result +=str();
                    break;
                case End_of_input:
                    result += str();
                    break;
                case Right_Braces:
                    result += str();
                    break;
                case Left_Braces:
                    result += str();
                    break;
                case Inclusion:
                    result += str();
                    break;
                case Start:
                    result += str();
                    break;
                case End:
                    result +=str();
                    break;
                case Logic_Operator:
                    result +=str();
                    break;
                case Line_Delimiter:
                    result +=str();
                    break;
                case relational_operator:
                    result += str();
                    break;
                case Access_Operator:
                    result += str();
                    break;
                case Left_Paren:
                    result += str();
                    break;
                case Right_Paren:
                    result +=str();
                    break;
                case Left_Brace:
                    result += str();
                    break;
                case Right_Brace:
                    result +=str();
                    break;
                case Op_divide:
                    result += str();
                    break;
                case Invalid:
                    result +=str();
                    break;
            }
            return result;
        }
        public String str()
        {
            return String.format("%10d  %35s  %50s %35d  %50s", this.line , value , this.tokentype , this.pos , this.match   );
        }
    }
 
    static enum TokenType 
    {
        End_of_input, Op_multiply,  Op_divide,Left_Paren, Right_Paren,Left_Brace, Right_Brace, 
        Comma, Identifier, Integer, String, commment,Condition,SInteger , Character, Float ,SFloat ,
        Loop ,Return ,Break ,Struct ,Inclusion ,Start,End,Arithmetic_Operation,Right_Braces,Left_Braces,
        Access_Operator, relational_operator,Assignment_operator,Constant,Quotation_Mark,Comment, Token_Delimiter,Line_Delimiter,
        Logic_Operator,Void,Invalid
    }
 
    static void error(int line, int pos, String msg,String match) 
    {
        if (line > 0 && pos > 0) 
        {
            System.out.printf("%s in line %d, pos %d\n", msg, line, pos,match);
        } 
        else 
        {
            System.out.println(msg);
        }
        System.exit(1);
    }
 
    SCANNER(String source) 
    {
        this.line = 1;
        this.pos = 0;
        this.position = 0;
        this.match = "match";
        this.s = source;
        this.chr = this.s.charAt(0);
        this.keywords.put("Imw", TokenType.Integer); 
        this.keywords.put("Yesif-Otherwise", TokenType.Condition);  
        this.keywords.put("Omw", TokenType.Integer); 
        this.keywords.put("SIMww", TokenType.SInteger); 
        this.keywords.put("Chji", TokenType.Character); 
        this.keywords.put("Seriestl", TokenType.String); 
        this.keywords.put("IMwf", TokenType.Float); 
        this.keywords.put("SIMwf", TokenType.SFloat); 
        this.keywords.put("NOReturn", TokenType.SFloat); 
        this.keywords.put("RepeatWhen", TokenType.Loop); 
        this.keywords.put("Reiterate", TokenType.Loop); 
        this.keywords.put("GetBack", TokenType.Return);
        this.keywords.put("OutLoop", TokenType.Break);
        this.keywords.put("Loli", TokenType.Struct);
        this.keywords.put("Include", TokenType.Inclusion);
        this.keywords.put("Start", TokenType.Start);
        this.keywords.put("Last", TokenType.End);
    }
  
    
    Token follow(char expect, TokenType ifyes, TokenType ifno,String ss,String s ,int line, int pos) 
    {
        if (getNextChar() == expect) 
        {
            getNextChar();
            return new Token(ifyes,ss, line, pos,"match");
        }
        if (ifno == TokenType.End_of_input) 
        {
            error(line, pos, String.format("follow: unrecognized character: (%d) '%c'", (int)this.chr, this.chr), "not match");
            listi(line);
            this.en+=1;
        }
        return new Token(ifno,s, line, pos,"match");
    }
    Token FollowComment(char expect,char expect1, TokenType ifyes, TokenType ifno,String ss,String s ,int line, int pos) 
    {
        if (getNextChar() == expect) 
        {
            getNextChar();
            return new Token(ifyes,ss, line, pos,"match");
        }
        if (getNextChar() == expect1) 
        {
            getNextChar();
            return new Token(ifyes,ss, line, pos,"match");
        }
        if (ifno == TokenType.End_of_input) 
        {
            error(line, pos, String.format("follow: unrecognized character: (%d) '%c'", (int)this.chr, this.chr), "not match");
            listi(line);
            this.en+=1;
        }
        return new Token(ifno,s, line, pos,"match");
    }

    Token div_or_comment(int line, int pos) 
    {
        if (getNextChar() != '@' && this.chr != '^' ) 
        {
            return new Token(TokenType.Op_divide, "/", line, pos,"match");
        }
          
        if (this.chr == '^' ) 
        {    
        getNextChar();
        while (true) 
        { 
            if (this.chr == '\u0000') 
            {
                error(line, pos, "EOF in comment","match");
                listi(line);
                this.en+=1;
            } 
            else if (this.chr == '\n') 
            {
               
                    getNextChar();
                    return new Token(TokenType.commment, "/^", line, pos,"match");
                   
              
                     
            }
            else 
            {
                getNextChar();
            }
        }
  
        }
        getNextChar();
        while (true) 
        { 
            if (this.chr == '\u0000') 
            {
                error(line, pos, "EOF in comment","match");
                listi(line);
                this.en+=1;
            } 
            else if (this.chr == '@') 
            {
                if (getNextChar() == '/') 
                {
                    getNextChar();
                    return new Token(TokenType.commment, "/@ @/", line, pos,"match");
                    //return getToken();
                }
                     
            }
            else 
            {
                getNextChar();
            }
        }
    }
    Token identifier_or_integer(int line, int pos) 
    {
        boolean is_number = true;
        String text = "";
 
        while (Character.isAlphabetic(this.chr) || Character.isDigit(this.chr) || this.chr == '_') 
        {
            text += this.chr;
            if (!Character.isDigit(this.chr)) 
            {
                is_number = false;
            }
            getNextChar();
        }

 
        if (Character.isDigit(text.charAt(0))) 
        {
            if (!is_number) 
            {
                this.en+=1;
                listi(line);
                return new Token(TokenType.Invalid, text, line, pos, "not match");
                //error(line, pos, String.format("invaslid number: %s", text), "not match");
            }
            return new Token(TokenType.Constant, text, line, pos, "match");
        }
 
        if (this.keywords.containsKey(text)) 
        {
            return new Token(this.keywords.get(text), text, line, pos, "match");
        }

        return new Token(TokenType.Identifier, text, line, pos, "match");
        
    }
    
    
    void listi(int line){
        
        this.li.add(line);
                
    }
    
    
    Token getToken() 
    {
        int line, pos;
        while (Character.isWhitespace(this.chr)) 
        {
            getNextChar();
        }
        line = this.line;
        pos = this.pos;

        switch (this.chr) 
        {
            
            case '\u0000': return new Token(TokenType.End_of_input, "", this.line, this.pos,"");
            case '/': return div_or_comment(line, pos);
            
            case '<': return follow('=', TokenType.relational_operator, TokenType.relational_operator,"<=","<",line, pos);
            case '-': return follow('>', TokenType.Access_Operator, TokenType.Arithmetic_Operation,"->","-", line, pos);
            case '>': return follow('=', TokenType.relational_operator, TokenType.relational_operator,">=",">", line, pos);
            case '=': return follow('=', TokenType.relational_operator, TokenType.Assignment_operator,"==","=", line, pos);
            case '!': return follow('=', TokenType.relational_operator, TokenType.Logic_Operator,"!=","!", line, pos);
            case '&': return follow('&', TokenType.Logic_Operator, TokenType.Logic_Operator,"&&","&", line, pos);
            case '|': return follow('|', TokenType.Logic_Operator, TokenType.Logic_Operator,"||","|", line, pos);
            case '~': getNextChar(); return new Token(TokenType.Logic_Operator, "~", line, pos, "match");
           
            case '{': getNextChar(); return new Token(TokenType.Left_Brace, "{", line, pos, "match");
            case '}': getNextChar(); return new Token(TokenType.Right_Brace, "}", line, pos, "match");
            case '(': getNextChar(); return new Token(TokenType.Left_Paren, "(", line, pos, "match");
            case ')': getNextChar(); return new Token(TokenType.Right_Paren, ")", line, pos, "match");
            case '[': getNextChar(); return new Token(TokenType.Right_Braces, "[", line, pos, "match");
            case ']': getNextChar(); return new Token(TokenType.Left_Braces, "]", line, pos, "match");
            case '+': getNextChar(); return new Token(TokenType.Arithmetic_Operation, "+", line, pos, "match");
            case '*': getNextChar(); return new Token(TokenType.Arithmetic_Operation, "*", line, pos, "match");
            case '%': getNextChar(); return new Token(TokenType.Arithmetic_Operation, "%", line, pos, "match");
            case ',': getNextChar(); return new Token(TokenType.Quotation_Mark, ",", line, pos, "match");
            case '$': getNextChar(); return new Token(TokenType.Token_Delimiter, "$", line, pos, "match");
            case '.': getNextChar(); return new Token(TokenType.Line_Delimiter, ".", line, pos, "match");
            case ';': getNextChar(); this.en+=1; listi(line);return new Token(TokenType.Invalid, ";", line, pos, "not match");
            case '"': getNextChar(); this.en+=1; listi(line);return new Token(TokenType.Invalid, " \" ", line, pos, "not match");
            case '\'':getNextChar(); this.en+=1; listi(line); return new Token(TokenType.Invalid, "'", line, pos, "not match");
            default: return identifier_or_integer(line, pos);
        } 
        
    }
    
    char getNextChar() 
    {
        this.pos++;
        this.position++;
        if (this.position >= this.s.length()) 
        {
            this.chr = '\u0000';
            return this.chr;
        }
        this.chr = this.s.charAt(this.position);
        if (this.chr == '\n') 
        {
            this.line++;
            this.pos =0;
        }
        return this.chr;
    }
 
    void printTokens() {
        Token t;
        String bar = "Line Number                 Lexeme                                    Return Token                Lexeme NO IN Line                        Matchability ";
        String s="";
        e.t.append(bar+"\n");
        while ((t = getToken()).tokentype != TokenType.End_of_input) 
        {
            s= String.valueOf(t);
            e.t.append(s+"\n");
            
        }
        e.t.append(s+"\n");
        
        e.t.append("    Total NO of errors: "+this.en+"\n");
        e.t.append("    Lines Of Error : "+String.valueOf(this.li));
        
    }
    
}