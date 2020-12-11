package controller;

public enum Mnemonics {
	
	STOP, RETTR, MOVSPA, MOVFLGA, BR, BRLE, BRLT, BREQ, BRNE, BRGE, BRGT, BRV, BRC,
	CALL, NOTA, NOTX, NEGA, NEGX, ASLA, ASLX, ASRA, ASRX, ROLA, ROLX, RORA, RORX,
	NOP, DECI, DECO, STRO, CHARI, CHARO, ADDSP, SUBSP, ADDA, SUBA, ANDA, ORA, CPA,
	LDA, LDBYTEA, STA, STBYTEA, i, d, n ,s, sf, x, sx, sfx, A, X, ADDRSS, ASCII,
	BLOCK, BURN, BYTE, END, EQUATE, WORD;
	

	public String getBinaryMnemonic(){
		switch(this) {
		case STOP -> 	{return 	"00000000";}
		case RETTR -> 	{return 	"00000001";}
		case MOVSPA -> 	{return 	"00000010";}
		case MOVFLGA -> {return 	"00000011";}
		
		case BR -> 		{return 	"0000010";}
		case BRLE -> 	{return 	"0000011";}
		case BRLT -> 	{return 	"0000100";}
		case BREQ -> 	{return 	"0000101";}
		case BRNE -> 	{return 	"0000110";}
		case BRGE -> 	{return 	"0000111";}
		case BRGT -> 	{return 	"0001000";}
		case BRV -> 	{return 	"0001001";}
		case BRC -> 	{return 	"0001010";}
		case CALL -> 	{return 	"0001011";}
		
		case NOTA -> 	{return 	"00011000";}
		case NOTX -> 	{return 	"00011001";}
		case NEGA -> 	{return 	"00011010";}
		case NEGX -> 	{return 	"00011011";}
		case ASLA -> 	{return 	"00011100";}
		case ASLX -> 	{return 	"00011101";}
		case ASRA -> 	{return 	"00011110";}
		case ASRX -> 	{return 	"00011111";}
		case ROLA -> 	{return 	"00100000";}
		case ROLX -> 	{return 	"00100001";}
		case RORA -> 	{return 	"00100010";}
		case RORX -> 	{return 	"00100011";}
		
		case NOP -> 	{return 	"00101";}
		case DECI -> 	{return 	"00110";}
		case DECO -> 	{return 	"00111";}
		case STRO -> 	{return 	"01000";}
		case CHARI -> 	{return 	"01001";}
		case CHARO -> 	{return 	"01010";}
		
//		case RETn
		
		case ADDSP -> 	{return 	"01100";}
		case SUBSP -> 	{return 	"01101";}
		
		case ADDA -> 	{return 	"0111";}
		case SUBA -> 	{return 	"1000";}
		case ANDA -> 	{return 	"1001";}
		case ORA -> 	{return 	"1010";}
		case CPA -> 	{return 	"1011";}
		case LDA -> 	{return 	"1100";}
		case LDBYTEA -> {return 	"1101";}
		case STA -> 	{return 	"1110";}
		case STBYTEA -> {return 	"1111";}
		
		case i -> 	{return 	"000";}
		case d -> 	{return 	"001";}
		case n  -> 	{return 	"010";}
		case s -> 	{return 	"011";}
		case sf -> 	{return 	"100";}
		case x -> 	{return 	"101";}
		case sx -> 	{return 	"110";}
		case sfx -> {return 	"111";}
		
		case A -> {return "0";}
		case X -> {return "1";}
		
		case ADDRSS -> 	{return "";}
		case ASCII -> 	{return "";}
		case BLOCK -> 	{return "";}
		case BURN -> 	{return "";}
		case BYTE -> 	{return "";}
		case END -> 	{return "";}
		case EQUATE -> {return "";}
		case WORD -> 	{return "";}
		
		default -> throw new IllegalArgumentException("This is an illegal mnemonic. \nPlease double check your code.");
		
		}
		
	}
	
	public boolean containsUMneumonics() {
		switch(this) {
		case STOP -> 	{return true;}
		case RETTR -> 	{return true;}
		case MOVSPA -> 	{return true;}
		case MOVFLGA -> {return true;}

		default -> {return false;}
		
		}
	}
	
	public boolean containsAMneumonics() {
		switch(this) {
		
		case BR -> 		{return true;}
		case BRLE -> 	{return true;}
		case BRLT -> 	{return true;}
		case BREQ -> 	{return true;}
		case BRNE -> 	{return true;}
		case BRGE -> 	{return true;}
		case BRGT -> 	{return true;}
		case BRV -> 	{return true;}
		case BRC -> 	{return true;}
		case CALL -> 	{return true;}
		
		default -> {return false;}
	
		}
		
	}
	
	public boolean containsRMneumonics() {
		switch(this) {
		
		case NOTA -> 	{return true;}
		case NOTX -> 	{return true;}
		case NEGA -> 	{return true;}
		case NEGX -> 	{return true;}
		case ASLA -> 	{return true;}
		case ASLX -> 	{return true;}
		case ASRA -> 	{return true;}
		case ASRX -> 	{return true;}
		case ROLA -> 	{return true;}
		case ROLX -> 	{return true;}
		case RORA -> 	{return true;}
		case RORX -> 	{return true;}
		
		default -> {return false;}
		
		}
	}
	
	public boolean containsAAAMneumonics() {
		switch(this) {
		
		case NOP -> 	{return true;}
		case DECI -> 	{return true;}
		case DECO -> 	{return true;}
		case STRO -> 	{return true;}
		case CHARI -> 	{return true;}
		case CHARO -> 	{return true;}
		
//		case RETn
		
		case ADDSP -> 	{return true;}
		case SUBSP -> 	{return true;}
		
		default -> {return false;}
		
		}
		
	}
	
	public boolean containsRAAAMneumonics() {
		switch(this) {
		
		case ADDA -> 	{return true;}
		case SUBA -> 	{return true;}
		case ANDA -> 	{return true;}
		case ORA -> 	{return true;}
		case CPA -> 	{return true;}
		case LDA -> 	{return true;}
		case LDBYTEA -> {return true;}
		case STA -> 	{return true;}
		case STBYTEA -> {return true;}
		
		default -> {return false;}
		
		}
		
	}
	
	public int getSingleAddrsMode() {
		switch(this) {
		case i -> {return 0;}
		case x -> {return 1;}
		default -> throw new IllegalArgumentException("This is an illegal addressing mode.");
		}
	}

	public boolean containsDotCommand(String firstCommand) {
		switch(this) {
		
		case ADDRSS -> 	{return true;}
		case ASCII -> 	{return true;}
		case BLOCK -> 	{return true;}
		case BURN -> 	{return true;}
		case BYTE -> 	{return true;}
		case END -> 	{return true;}
		case EQUATE -> {return true;}
		case WORD -> 	{return true;}
		
		default -> {return false;}
		
		}
		
	}

}
