 

import java.io.*;

class Dijkstra {

public static void main(String[] args) throws IOException {

	
		//declaring string to read input file
		String line;
		
		//Reading input from file
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		//reading first line and declaring that as dimensions
		line = br.readLine();
		int dimensions = Integer.parseInt(line);
		/*System.out.println(dimensions);*/
		
		//creating matrix using dimensions which are read in above code
		int matrix[][] = new int[dimensions][dimensions];
		
		//filling the matrix with infinity
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		
		// reading from second line of input file and updating the matrix 
		while ((line = br.readLine()) != null) {


			String[] splitted = line.split(" ");
			String i_value_sample = splitted[0].split(",")[0];
			String j_value_sample = splitted[0].split(",")[1];
			String value_sample = splitted[1];

			/*int i_value = Integer.parseInt(i_value_sample);
			int j_value = Integer.parseInt(j_value_sample);
			int value = Integer.parseInt(value_sample);*/
			
			/*char i_value = line.charAt(0);
			char j_value = line.charAt(2);
			char value = line.charAt(4);*/
			matrix[Integer.parseInt(i_value_sample)][Integer.parseInt(j_value_sample)] = Integer.parseInt(value_sample);
		}
		
		//close reading 
		br.close();
		
		
//printing matrix
		
//final matrix
		//int final_matrix[][]=new int [dimensions][dimensions];
		
		//making object of class
		//Dijkstra dk = new Dijkstra();
		//getting final matrix
		//final_matrix= Dijkstra_implement(matrix,dimensions,final_matrix);
		int predecessor_dist[][] = Dijkstra_implement(matrix,dimensions);
		int predecessor[] = new int[dimensions];
		int dist[] = new int[dimensions];
		
		for(int j=0;j<dimensions;j++)
		{
			predecessor[j]=	predecessor_dist[0][j];
			 dist[j]=predecessor_dist[1][j];
		}
		
		/*System.out.println("given nodes,predecessor and cost are");
		System.out.print("node ");
		System.out.print("predecessor ");
		System.out.println("cost to reach");
		
		for(int j=1;j<dimensions;j++)
		{
			
			System.out.print("  "+ predecessor[j] +"           ");
			System.out.print(j+"    ");
			System.out.println("  "+dist[j]);
		}
		
		System.out.println(" %%%% ");
		System.out.println(" %%%%% ");
		*/
		
		//for writing to the file
		BufferedWriter writer = new BufferedWriter( new FileWriter("output.txt"));
		for(int j=dimensions-1;j>0;j--)
		{	
			String s1="";
			int i =j;
			while(i>=0)
			{
				s1=i+"->"+s1;
				//System.out.print(i+"<-");
				i = predecessor[i];
				//System.out.print("<-"+i);	
			}
			int t = s1.length()-2;
			s1 = s1.substring(0,t)+ ":"+dist[j];
			//writing output to console
				System.out.print(s1+"\n");	
			//writing result to output file	
				writer.write(s1);
				writer.newLine();
			
		}
		//closing writer
			writer.close();
		//printing input matrix
		//matrix(matrix,dimensions);
		//printing final matirx
		//matrix(final_matrix,dimensions);
}

private static int[][] Dijkstra_implement(int[][] matrix, int dimensions) {

	/*//3	//creating vertex set 
	Set<Integer> set = new HashSet<Integer>();
	for(int i=0;i<dimensions;i++)
	{
		set.add(i);
		
	}*/
	
	
	
//6	//distance array max value 
//7  //prev[v] <- infinity
	int dist[]= new int[dimensions];
	int predecessor[] = new int[dimensions];
			for(int i=0;i<dimensions;i++)
			{
				dist[i]=Integer.MAX_VALUE;
				predecessor[i]=-1;
			}
			

	
//10. initializing source and source distance
			
	dist[0]=0;
	int cur_node = 0;
	
	//int previous_node = 0;
	int cur_cost = 0;
	//int min_find = 0;
	
//for handling equal problem
	//int equal_problem =0;
//converting set to list
	
//12.
	int k = dimensions;
	while(k!= 0)
	{	
		
		// declaring alt
		int alt[] = new int[dimensions];
		for(int j=0; j<dimensions;j++)
		{
			int i = cur_node;

			alt[j] = cur_cost + matrix[i][j];
			if(alt[j]<0)
			{
				alt[j]=Integer.MAX_VALUE;
			}
		}//end of for loop
		
	//pushing values to dist array
		for(int j=0; j<dimensions;j++)
		{
			if(alt[j]<dist[j])
			{
				dist[j]=alt[j];
				predecessor[j]=cur_node;
			}
		}//for loop end
		
		//pushing dist to final_matrix
		/*for(int j=0; j<dimensions;j++)
		{
			int i = cur_node;
			{
				final_matrix[i][j]=dist[j];
			}
		}*///for loop end
		
		//getting min of distances
		int[] min_and_index= getMin(dist,cur_cost,cur_node);
		//min_find  =min_and_index[0];
		cur_cost=min_and_index[0];
		cur_node = min_and_index[1];
		
		
		//equal_problem = cur_node;
		
		
		//finding node that contains min cost
		
		//removing nodes
		//set.remove(cur_node);
		
		k--;

	}//while end
	
	
	int[][] predecessor_dist = new int[2][dimensions];
	for(int j=0;j<dimensions;j++)
	{
		predecessor_dist[0][j]= predecessor[j];
		predecessor_dist[1][j] = dist[j];
	}
	return predecessor_dist;
}


static int[] getMin(int[] temp_array,int cur_cost,int cur_node){ 
    int min_value =	Integer.MAX_VALUE; 
    int index= -1;
    for(int j=0;j<temp_array.length;j++){ 
      if(temp_array[j] < min_value && temp_array[j]>=cur_cost && j!=cur_node){ 
    	  min_value = temp_array[j];
    	  index = j;
      } 
    } 
    int []min_and_index={min_value,index};
    return min_and_index; 
}


/*private static void matrix(int[][] matrix, int dimensions) {
	
	System.out.println("");
	System.out.println("matrix is ");
	
	for (int i = 0; i < dimensions; i++) {
		for (int j = 0; j < dimensions; j++) {
				if (i==j){
					final_matrix[i][j]=0;
				}
				System.out.print(matrix[i][j]+ " ");
				
			}
		System.out.println("");
	}
}*/
}
