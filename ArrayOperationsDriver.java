import java.security.SecureRandom;


//Luis Lopez - Assigment 1
//COSC 3331 - Fall 2021

public class ArrayOperationsDriver {

	public static void main(String[] args) {
		
		SecureRandom randomNumGenerator = new SecureRandom();//SecureRandom number generator for number of elements to store on the arrays
		
		final int arrSIZE = 50;
		final int tallySIZE = 101;
		int elementSizeA, elementSizeB, combinedElementSize,combinedPos = 0, maxVal;
		int[] ListA = new int [arrSIZE];
		int[] ListB = new int [arrSIZE];
		int[] TheListA,TheList;
		elementSizeA = (randomNumGenerator.nextInt(49)) + 1;//number of elements to populate ListA with random number in the range of 1-49
		elementSizeB = (randomNumGenerator.nextInt(49)) + 1;//number of elements to populate ListB with random number in the range of 1-49
		int [] tallyArrayA = new int [tallySIZE];// tally array for making sure they are no duplicates while creating random numbers for ListA
		int [] tallyArrayB = new int [tallySIZE];// tally array for making sure they are no duplicates while creating random numbers for ListB
		
		for(int i = 0; i < tallySIZE; i++) {//populate tally arrays to all 0's
			
			tallyArrayA[i] = 0;
			tallyArrayB[i] = 0;
		}
			
		
		
		for(int i = 0; i < elementSizeA; i++) {//populating ListA with random numbers and ensuring no duplicates
			
			int tempRandomNum = randomNumGenerator.nextInt(101);//generate a random number in the range 0-100
			
			ListA[i] = tempRandomNum;
			
			tallyArrayA[tempRandomNum]++;//tally random number in the tally array
			
			while(tallyArrayA[tempRandomNum] > 1) {//checking for duplicates and if found generate a new random number
				
				tallyArrayA[tempRandomNum]--;
				
				tempRandomNum = randomNumGenerator.nextInt(101);//generate a random number in the range 0-100
				
				ListA[i] = tempRandomNum;
				
				tallyArrayA[tempRandomNum]++;//tally random number in the tally array
			}
		}
		
		for(int i = 0; i < elementSizeB; i++) {//populating LisB with random numbers and ensuring no duplicates
			
			int tempRandomNum = randomNumGenerator.nextInt(101);//generate a random number in the range 0-100
			
			ListB[i] = tempRandomNum;
			
			tallyArrayB[tempRandomNum]++;
			
			while(tallyArrayB[tempRandomNum] > 1) {//checking for duplicates and if found generate a new random number
				
				tallyArrayB[tempRandomNum]--;
				
				tempRandomNum = randomNumGenerator.nextInt(101);//generate a random number in the range 0-100
				
				ListB[i] = tempRandomNum;
				
				tallyArrayB[tempRandomNum]++;	
			}
		}
		
		
		
		System.out.println("Original ListA:");
		for(int i = 0; i < elementSizeA; i++) {
			
			System.out.printf("%d   ",ListA[i]);
		}
		
		System.out.println();
		System.out.println();
		
		
		System.out.println("Original ListB:");
		for(int i = 0; i < elementSizeB; i++) {
			
			System.out.printf("%d   ",ListB[i]);
		}
		
		System.out.println();
		System.out.println();
		
		
		//Part I.a Finding common elements in both arrays and remove them.
		System.out.println("Common values in both arrays:");
		for(int i = 0; i < tallySIZE; i++) {//Traverse only ONE time in both tally arrays simultaneously to look for common elements in both arrays.
			
			if(tallyArrayA[i] == 1 && tallyArrayB[i] == 1) {//if common is found, find that value and remove from each array then decrease the elementSize

				System.out.printf("%d    ",i);//Printing out each common value already in order because it's from the tally array which is already ordered by the nature of the elements.
				
				for(int a = 0; a < elementSizeA; a++) {//traverse throught ListA and find the common value, then remove and decrease elementSize
					
					if(ListA[a] == i) {//if common value found
						
						for(int k=a; k < elementSizeA; k++) {//"delete" the value
							ListA[k] = ListA[k+1];
						}
					
					elementSizeA--;//decrease the element size
						
					}
		
				}
				
				for(int b = 0; b < elementSizeB; b++) {//traverse throught ListB and find the common value, then remove and decrease elementSize
					
					if(ListB[b] == i) {//if common value found
						
						for(int k=b; k < elementSizeB; k++) {//"delete" the value
							ListB[k] = ListB[k+1];
						}
					
					elementSizeB--;//decrease the element size
						
					}
					
				}
				
			}
			
		}
		
		System.out.println();
		System.out.println();
		
		//Part I.b Display common elements of the arrays IN ORDER
		
		System.out.println("Updated ListA with no common values:");
		for(int i = 0; i < elementSizeA; i++) {
			
			System.out.printf("%d   ",ListA[i]);
		}
		
		System.out.println();
		System.out.println();
		
		System.out.println("Updated ListB with no common values:");
		for(int i = 0; i < elementSizeB; i++) {
			
			System.out.printf("%d   ",ListB[i]);
		}
		
		System.out.println();
		System.out.println();
		
		combinedElementSize = elementSizeA + elementSizeB;
		TheListA = new int [combinedElementSize]; //initialize the new array with the updated elementsizes of both ListA and ListB.
		TheList = new int [combinedElementSize];
		
		for (int i = 0;i < elementSizeA; i++) {//First merging ListA into TheListA
			TheListA[combinedPos] = ListA[i];
			combinedPos++;
		}
		for (int i = 0;i < elementSizeB; i++) {//then merging ListB into TheListA
			TheListA[combinedPos] = ListB[i];
			combinedPos++;
		}
		
		//Counting-Sort for sorting the new TheList array. We need k-maxValue, n-size of array, A-Input array TheListA, B-output array TheList(output)
		maxVal = maxValue(TheListA, combinedElementSize);
		
		for(int i = 0; i < combinedElementSize; i++) {//set the output array to all zeroes
			
			TheList[i] = 0;
		}
		
		CountingSort(TheListA, TheList, combinedElementSize - 1, maxVal + 1);
	
		
		System.out.println("TheList:");
		for(int i = 0; i < combinedElementSize; i++) {
			
			System.out.printf("%d  ",TheList[i]);
		}
		System.out.println();
		System.out.println();
		System.out.println("Part II - int-array-game\n");
		//PART II int-array-game
		int sumValues = 0,countSkips = 0;
		
		if(elementSizeA == elementSizeB) {// List A is key
			
			for(int i = 0; i < elementSizeA; i++) {// iterate through key array ListA
				
				if(ListA[i] < elementSizeB) {//checking if key array value is within range of elements in ListB.
					
					sumValues += ListB[ListA[i]];//sum up values
					
				}
				else {//if its not within range, we skip it
					countSkips++;
				}
				
			}
			
		}
		else if(elementSizeA < elementSizeB) {// List A is key
			
			for(int i = 0; i < elementSizeA; i++) {// iterate through key array ListA
				
				if(ListA[i] < elementSizeB) {//checking if key array value is within range of elements in ListB.
					
					sumValues += ListB[ListA[i]];//sum up values
					
				}
				else {//if its not within range, we skip it
					countSkips++;
				}
				
			}
			
		}
		else if(elementSizeB < elementSizeA) {// List B is key
			
			for(int i = 0; i < elementSizeB; i++) {//iterate through key array ListB
				
				if(ListB[i] < elementSizeA) {//checking if key values is within range of elements in ListA
					
					sumValues += ListA[ListB[i]];//sum up values
					
				}
				else {//if its not within range, we skip it
					countSkips++;
				}
				
			}
			
		}
		
		System.out.println("List A: ");
		for(int i = 0; i < elementSizeA; i++) {
			
			
			System.out.printf("%d ", ListA[i]);
			
		}
		System.out.println();
		System.out.println("List B: ");
		for(int i = 0; i < elementSizeB; i++) {
			
			
			System.out.printf("%d ", ListB[i]);
			
		}
		System.out.println();
		System.out.println();
		System.out.printf("Target Sum: %d%nElements skipped:%d", sumValues, countSkips);
		

	}

	public static void CountingSort(int []Aarr, int[] Barr,int n, int k) {
		
		int [] Carr = new int [k];// auxiliary storage
		
		for (int i = 0; i < k; i++)// storage populated with all zeros
			Carr[i] = 0;
		
		
		for(int j = 0; j < n; j++) {//tallying up storage array with values of the input array
			
			Carr[Aarr[j]] = Carr[Aarr[j]] + 1;
			
		}
		
		
		for(int i = 1; i < k; i++) {// adds up each index with the index before it
			
			Carr[i] = Carr[i] + Carr[i-1];
		}
		
		for(int j = n; j >= 0; j--) {// places each element of the input arrays into the coorect sorted position in the output array.
			
			Barr[Carr[Aarr[j]]] = Aarr[j];
			Carr[Aarr[j]] = Carr[Aarr[j]] - 1;
			
		}
		
	}

	public static int maxValue(int [] arr, int size) {//finds maximum value in an array
		
		int maxVal = 0;
		
		for(int i = 0; i < size; i++) {
			
			if(arr[i] > maxVal) {
				maxVal = arr [i];
			}
			
		}
		
		return maxVal;
		
	}
	
}


