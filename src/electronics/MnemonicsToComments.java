package electronics;

import java.util.ArrayList;
import java.util.HashMap;

public class MnemonicsToComments {
	public static String getCorrectOperand(String operand) {
		switch(operand) {
		case "A":
			return "accumulator";
		case "B":
			return "register B";
		case "C":
			return "register C";
		case "D":
			return "register D";
		case "E":
			return "register E";
		case "H":
			return "register H";
		case "L":
			return "register L";
		case "M":
			return "memory";
		case "SP":
			return "stack pointer";
		default:
			try{
				Integer.parseInt(operand);
				return operand+"H";
			}
			catch(Exception e) {
				return operand;
			}
		}
	}
	
	public static String getCorrectOperandPair(String operand) {
		switch(operand) {
		case "B":
			return "BC";
		case "D":
			return "DE";
		case "H":
			return "HL";
		case "SP":
			return "stack pointer";
		default:
			return operand;
		}
	}
	
	public static String[] getTwoOperands(String mnemonicString,String toModify) {
		int startingIndexOfOperand=mnemonicString.indexOf(' ')+1;
		int endingIndexOfOperand=mnemonicString.indexOf(',');
		String firstOperand=mnemonicString.substring(startingIndexOfOperand, endingIndexOfOperand);
		startingIndexOfOperand=endingIndexOfOperand+1;
		String secondOperand=mnemonicString.substring(startingIndexOfOperand);
		secondOperand=getCorrectOperand(secondOperand);
		if(!toModify.contains("register pair")) {
			firstOperand=getCorrectOperand(firstOperand);
		}
		else {
			firstOperand=getCorrectOperandPair(firstOperand);
		}
		String[] operands= {
				secondOperand,firstOperand
		};
		return operands;
	}
	public static String getOneOperand(String mnemonicString,String toModify) {
		int indexOfOperand=mnemonicString.indexOf(' ')+1;
		String operand=mnemonicString.substring(indexOfOperand);
		if(!toModify.contains("register pair")) {
			operand=getCorrectOperand(operand);
		}
		else {
			operand=getCorrectOperandPair(operand);
		}
		return operand;
	}
	
	public static void commentMnemonic(String mnemonicString,ArrayList<HashMap<String,String>> commandsMappingList) {
		int noOfOperand=0;
		for(HashMap<String,String> commandsMapping:commandsMappingList) {
			for(String command:commandsMapping.keySet()) {
				if(mnemonicString.contains(command)) {
					
					String commandToModify=commandsMapping.get(command);
					//System.out.print(mnemonicString+"\t\t");	
					if(noOfOperand==1) {
						String operand=getOneOperand(mnemonicString,commandToModify);
						if(operand.compareTo("stack pointer")==0) {
							commandToModify=commandToModify.replace("register pair", " ");
						}
						commandToModify=commandToModify.replaceFirst("\\[]", operand);
					}
					else if(noOfOperand==2) {
						String[] operands=getTwoOperands(mnemonicString,commandToModify);
						for(String operand:operands) {
							commandToModify=commandToModify.replaceFirst("\\[]", operand);
							if(operand.compareTo("stack pointer")==0) {
								commandToModify=commandToModify.replace("register pair", " ");
							}
						}
					}
					System.out.println(commandToModify);
					return;
				}
			}
			noOfOperand++;
		}
		System.out.println(mnemonicString+"\t\t"+"---------------");	
	}
	
	public static void commentAll(String mnemonicsString) {
		ArrayList<HashMap<String,String>> commandsList=getCommandsList();
		int fromIndex=0;
		int mnemonicEndIndex;
		do{
			mnemonicEndIndex=mnemonicsString.indexOf("\n",fromIndex);
			if(mnemonicEndIndex==-1) {
				break;
			}
			String mnemonic=mnemonicsString.substring(fromIndex, mnemonicEndIndex-1);
			mnemonic=mnemonic.trim();
			commentMnemonic(mnemonic,commandsList);
			fromIndex=mnemonicEndIndex+1;
		}
		while(true);
		String mnemonic=mnemonicsString.substring(fromIndex);
		mnemonic=mnemonic.trim();
		commentMnemonic(mnemonic,commandsList);
	}
	
	public static ArrayList<HashMap<String,String>> getCommandsList(){
		HashMap<String,String> commandsTwoOperandsMapping=getTwoOperandCommandList();
		HashMap<String,String> commandsOneOperandMapping=getOneOperandCommandList();
		HashMap<String,String> commandsNoOperandMapping=getNoOperandCommandList();
		ArrayList<HashMap<String,String>> commandsList=new ArrayList<>();
		commandsList.add(commandsNoOperandMapping);
		commandsList.add(commandsOneOperandMapping);
		commandsList.add(commandsTwoOperandsMapping);
		return commandsList;
	}
	
	public static HashMap<String,String> getTwoOperandCommandList(){
		HashMap<String,String> commandsMap=new HashMap<>();
		String[] normalStringCommands= {
			"MOV","MVI","LXI"	
		};
		String[] commandComments= {
			"Copy value at [] into []",
			"Copy value [] into []",
			"Copy value [] into [] register pair"
		};
		for(int i=0;i<normalStringCommands.length;i++) {
			commandsMap.put(normalStringCommands[i],commandComments[i]);
		}
		return commandsMap;
	}
	
	public static HashMap<String,String> getOneOperandCommandList(){
		HashMap<String,String> commandsMap=new HashMap<>();
		String[] normalStringCommands= {
			"CMP","INX","DCR","INR","JNZ","JZ","JP","JM","CPI","STA","LDA","ADD","SUB","JPO","ANI","JC","SBB","DAD","JNC","DCX","PUSH","POP","ADC"
		};
		String[] commandComments= {
			"Compare value at accumulator with value at []",
			"Increment value at [] register pair",
			"Decrement value at []",
			"Increment value at []",
			"If result is not zero then jump to []",
			"If result is zero then jump to []",
			"If result is positive then jump to []",
			"If result is negative then jump to []",
			"Compare value at accumulator with value []",
			"Store value at accumulator into memory location []",
			"Load value at memory location [] into accumulator",
			"Add value at [] to accumulator",
			"Subtract value at [] from accumulator",
			"If result has odd parity then jump to []",
			"And value at accumulator with value []",
			"If carry is generated then jump to []",
			"Subtract value at [] from accumulator with borrow",
			"Add [] register pair into HL register pair",
			"If no carry is generated then jump to []",
			"Decrement [] register pair",
			"Push value at [] register pair to stack",
			"Pop value from stack to [] register pair",
			"Add [] with carry to accumulator"
		};
		for(int i=0;i<normalStringCommands.length;i++) {
			commandsMap.put(normalStringCommands[i],commandComments[i]);
		}
		return commandsMap;
	}
	
	public static HashMap<String,String> getNoOperandCommandList(){
		HashMap<String,String> commandsMap=new HashMap<>();
		String[] normalStringCommands= {
			"STC","CMC","RAL","RAR"
		};
		String[] commandComments= {
			"Set carry flag",
			"Compliment carry flag",
			"Rotate accumulator left with carry",
			"Rotate accumulator right with carry"
		};
		for(int i=0;i<normalStringCommands.length;i++) {
			commandsMap.put(normalStringCommands[i],commandComments[i]);
		}
		return commandsMap;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String myMnemonics="	   LDA 2510\r\n" + 
				"	   CPI 00\r\n" + 
				"	   JNZ CHECKDENOMINATOR\r\n" + 
				"	   LDA 2511\r\n" + 
				"	   CPI 00\r\n" + 
				"	   JZ END\r\n" + 
				"\r\n" + 
				"	   LDA 2501\r\n" + 
				"	   CPI 00\r\n" + 
				"	   JZ END\r\n" + 
				"	   STA 3001\r\n" + 
				"	   STA 3006\r\n" + 
				"	   MVI A,00\r\n" + 
				"	   STA 2700\r\n" + 
				"	   STA 2710\r\n" + 
				"	   STA 3000\r\n" + 
				"	   STA 3005\r\n" + 
				"	   MVI A,01\r\n" + 
				"	   STA 2701\r\n" + 
				"	   STA 2711\r\n" + 
				"\r\n" + 
				"	   LDA 2700\r\n" + 
				"	   MOV H,A\r\n" + 
				"	   LDA 2701\r\n" + 
				"	   MOV L,A\r\n" + 
				"	   DAD H\r\n" + 
				"	   MOV A,H\r\n" + 
				"	   STA 2700\r\n" + 
				"	   MOV A,L\r\n" + 
				"	   STA 2701\r\n" + 
				"	   LDA 3000\r\n" + 
				"	   MOV H,A\r\n" + 
				"	   LDA 3001\r\n" + 
				"	   MOV L,A\r\n" + 
				"	   DAD H\r\n" + 
				"	   MOV A,H\r\n" + 
				"	   STA 3000\r\n" + 
				"	   MOV A,L\r\n" + 
				"	   STA 3001\r\n" + 
				"	   LDA 2510\r\n" + 
				"	   CMP H\r\n" + 
				"	   JZ CHECKLOWER\r\n" + 
				"	   JP STORE\r\n" + 
				"	   JM CALCULATE\r\n" + 
				"\r\n" + 
				"                       LDA 2511\r\n" + 
				"	   CMP L\r\n" + 
				"	   JZ FINALSTORE\r\n" + 
				"	   JM CNF\r\n" + 
				"	   JC CALCULATE\r\n" + 
				"\r\n" + 
				"	   LDA 3000\r\n" + 
				"	   STA 3005\r\n" + 
				"	   LDA 3001\r\n" + 
				"	   STA 3006\r\n" + 
				"	   LDA 2700\r\n" + 
				"	   STA 2710\r\n" + 
				"	   LDA 2701\r\n" + 
				"	   STA 2711\r\n" + 
				"	   JMP MAXIMIZE\r\n" + 
				"\r\n" + 
				"	   LDA 3000\r\n" + 
				"	   STA 3005\r\n" + 
				"	   LDA 3001\r\n" + 
				"	   STA 3006\r\n" + 
				"	   LDA 2700\r\n" + 
				"	   STA 2710\r\n" + 
				"	   LDA 2701\r\n" + 
				"	   STA 2711\r\n" + 
				"\r\n" + 
				"	   LDA 2510\r\n" + 
				"	   MOV H,A\r\n" + 
				"	   LDA 2511\r\n" + 
				"	   MOV L,A\r\n" + 
				"	   LDA 3005\r\n" + 
				"	   MOV B,A\r\n" + 
				"	   LDA 3006\r\n" + 
				"	   MOV C,A\r\n" + 
				"	   MOV A,L\r\n" + 
				"	   SUB C\r\n" + 
				"	   JZ UNSETCARRY\r\n" + 
				"	   JP UNSETCARRY\r\n" + 
				"	   JMP AFTERUNSET\r\n" + 
				"\r\n" + 
				"	   STC\r\n" + 
				"	   CMC\r\n" + 
				"\r\n" + 
				"	   MOV L,A\r\n" + 
				"	   MOV A,H\r\n" + 
				"	   SBB B\r\n" + 
				"	   MOV H,A\r\n" + 
				"	   JZ BEFOREROTATE\r\n" + 
				"	   JM ROTATE\r\n" + 
				"\r\n" + 
				"	   MOV A,H\r\n" + 
				"	   STA 2510\r\n" + 
				"	   MOV A,L\r\n" + 
				"	   STA 2511\r\n" + 
				"	   LDA 4001\r\n" + 
				"	   LXI H,2711\r\n" + 
				"	   ADD M\r\n" + 
				"	   STA 4001\r\n" + 
				"	   LDA 4000\r\n" + 
				"	   LXI H,2710\r\n" + 
				"	   ADC M\r\n" + 
				"	   STA 4000\r\n" + 
				"\r\n" + 
				"	   LDA 2711\r\n" + 
				"	   CPI 01\r\n" + 
				"	   JZ END\r\n" + 
				"	   LDA 2510\r\n" + 
				"	   CPI 00H\r\n" + 
				"	   JZ CHECK\r\n" + 
				"	   JMP NUMNOTZERO\r\n" + 
				"	   LDA 2511\r\n" + 
				"	   CPI 00H\r\n" + 
				"	   JZ END\r\n" + 
				"	   LDA 3005\r\n" + 
				"	   STC\r\n" + 
				"	   CMC\r\n" + 
				"	   RAR\r\n" + 
				"	   STA 3005\r\n" + 
				"	   LDA 3006\r\n" + 
				"	   RAR\r\n" + 
				"	   STA 3006\r\n" + 
				"	   LDA 2710\r\n" + 
				"	   STC\r\n" + 
				"	   CMC\r\n" + 
				"	   RAR\r\n" + 
				"	   STA 2710\r\n" + 
				"	   LDA 2711\r\n" + 
				"	   RAR\r\n" + 
				"	   STA 2711\r\n" + 
				"	   JMP CALCULATE\r\n";
		commentAll(myMnemonics);
	}

}
