package org.happybean;

public class slotTest {
	
	int[][] spinResult = {{5,0,6}, {4,5,0}, {4,5,-1}, {5,0,6}, {4,5,0}};
	
	public int payout(int[][] spinResult){
		
		int maxResult = 0;
		
		int[] matchLine1 = {spinResult[0][0], spinResult[1][0], spinResult[2][0], spinResult[3][0], spinResult[4][0]};
		int[] matchLine2 = {spinResult[0][1], spinResult[1][1], spinResult[2][1], spinResult[3][1], spinResult[4][1]};
		int[] matchLine3 = {spinResult[0][2], spinResult[1][2], spinResult[2][2], spinResult[3][2], spinResult[4][2]};
		int[] matchLine4 = {spinResult[0][0], spinResult[1][1], spinResult[2][2], spinResult[3][1], spinResult[4][0]};
		int[] matchLine5 = {spinResult[0][2], spinResult[1][1], spinResult[2][0], spinResult[3][1], spinResult[4][2]};
		
		int result[] = {validateMatchLine(matchLine1), validateMatchLine(matchLine2), validateMatchLine(matchLine3), validateMatchLine(matchLine4), validateMatchLine(matchLine5)};
		
		for(int i=0 ; i<result.length ; i++){
			if(maxResult <= result[i]){
				maxResult = result[i];
			}
		}
		
		System.out.println("========="+maxResult+"=========");
		
		return maxResult;
	}
	
	public int validateMatchLine(int[] matchLine){
		
		int prevItem = 2;		//�������� �ʴ� item ���ڷ� �ʱ�ȭ
		int currentItem = 2;	
		
		int cashPrize = 0;
		
		boolean isWin = true;
		
		boolean isContinuitySame7 = true;
		
		int[] setWildArr = matchLine;
		
		for(int i=1 ; i < setWildArr.length ; i++){
			prevItem = setWildArr[i-1];
			currentItem = setWildArr[i];	
			
			if(wildCheck(currentItem)){
				setWildArr[i] = prevItem;
				currentItem = prevItem;	// ���� ���Ұ� wild�̸� ���� ������ ������ ġȯ�Ѵ�.
			}
			
			if((sevenCheck(prevItem)&&sevenCheck(currentItem))){
				// 7�� 7����
				if((prevItem==currentItem)&&isContinuitySame7){
					if(i==2){	//���� 3�� ���� 
						if(currentItem==3){	//red7
							cashPrize = 8;
						}else if(currentItem==4){	//blue7
							cashPrize = 6;
						}else if(currentItem==5){	//white7
							cashPrize = 4;
						}
					}else if(i==3){	//���� 4�� ���� 
						if(currentItem==3){	//red7
							cashPrize = 25;
						}else if(currentItem==4){	//blue7
							cashPrize = 20;
						}else if(currentItem==5){	//white7
							cashPrize = 12;
						}
					}else if(i==4){	//���� 5�� ���� 
						if(currentItem==3){	//red7
							cashPrize = 100;
						}else if(currentItem==4){	//blue7
							cashPrize = 60;
						}else if(currentItem==5){	//white7
							cashPrize = 40;
						}
					}					
				}else{
					isContinuitySame7 = false;
					// ���� �ٸ� ���� 7
					if(i==2){	//���� 3�� ���� 
						cashPrize = 2;
					}else if(i==3){	//���� 4�� ���� 
						cashPrize = 7;
					}else if(i==4){	//���� 5�� ���� 
						cashPrize = 25;
					}
				}
				
			}else if((cherryCheck(prevItem)&&cherryCheck(currentItem))){
				// cherry�� cherry ����
				if(i==2){	//cherry ���� 3�� ���� 
					cashPrize = 1;
				}else if(i==3){	//cherry ���� 4�� ���� 
					cashPrize = 3;
				}else if(i==4){	//cherry ���� 5�� ���� 
					cashPrize = 10;
				}
			}else if(wildCheck(prevItem)&&sevenCheck(currentItem)){
				// wild ���� ���� 7���� 
				setWildArr = setWild(setWildArr, i, currentItem);
				if(i==2){
					if(currentItem==3){
						cashPrize = 8;
					}else if(currentItem==4){
						cashPrize = 6;
					}else if(currentItem==5){
						cashPrize = 4;
					}
				}else if(i==3){
					if(currentItem==3){
						cashPrize = 25;
					}else if(currentItem==4){
						cashPrize = 20;
					}else if(currentItem==5){
						cashPrize = 12;
					}
				}else if(i==4){
					if(currentItem==3){
						cashPrize = 100;
					}else if(currentItem==4){
						cashPrize = 60;
					}else if(currentItem==5){
						cashPrize = 40;
					}
				}
			}else if(wildCheck(prevItem)&&cherryCheck(currentItem)){
				// wild ���� ���� cherry���� 
				setWildArr = setWild(setWildArr, i, currentItem);
				if(i==2){
					cashPrize = 1;
				}else if(i==3){
					cashPrize = 3;
				}else if(i==4){
					cashPrize = 10;
				}
			}else if(wildCheck(prevItem)&&wildCheck(currentItem)){
				// wild - wild ����
				if(i==4){
					cashPrize = 1000;
				}
			}else{
				isWin = false;
			}
			
			if(!isWin){
				break;
			}
		}
		return cashPrize;
	}
	
	// ��� ���� ���ҵ��� wild��� �� ������ ���ҷ� ���� 
	public int[] setWild(int[] matchLine, int index, int item){
		for(int i=0 ; i<=index ; i++){
			matchLine[i] = item;
		}
		return matchLine;
	}
	
	// ���� ������� 7���� ���� 
	public boolean sevenCheck(int item){
		boolean result = false;
		if(item==3 || item==4 || item==5){
			result = true;
		}
		return result;
	}
	
	// wild ���� 
	public boolean wildCheck(int item){
		boolean result = false;
		if(item==0 || item==-1){
			result = true;
		}
		return result;
	}
	
	// cherry ���� 
	public boolean cherryCheck(int item){
		boolean result = false;
		if(item==6){
			result = true;
		}
		return result;
	}

}
