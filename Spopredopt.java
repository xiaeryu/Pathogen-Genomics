import java.io.*;

public class Spopredopt{
        public int calc(int setLen, int[] allLen){
        // Calculate the number of bases that can be preserved with the setLen as the cut-off.
                int total=0;
                for(int i=0; i<allLen.length; i++){
                        if(allLen[i] >= setLen){
                                total += 1;
                        }
                }
                total *= setLen;
                return total;
        }

        public int trace(int max, int[] array){
        // Trace the index of the maximum value of an array.
                for(int i=0; i<= array.length; i++){
                        if(array[i] == max){
                                return i;
                        }
                }
                return -1;
        }

        public static int getMaxValue(int[] numbers){
        // Get the maximum value of an array
                int maxValue = numbers[0];
                for(int i=1;i < numbers.length;i++){
                        if(numbers[i] > maxValue){
                                maxValue = numbers[i];
                        }
                }
                return maxValue;
        }

        public static int getMinValue(int[] numbers){
        // Get the minimum value of an array
                int minValue = numbers[0];
                for(int i=1;i<numbers.length;i++){
                        if(numbers[i] < minValue){
                                minValue = numbers[i];
                        }
                }
                return minValue;
        }

        public static void main(String args[]) throws IOException{
                // Parse command line arguments.
                String infile="";
                if (args.length == 1){
                        infile = args[0];
                }else{
                        System.err.println("Usage: <input fastq file>");
                        System.exit(1);
                }

                String storage=""; // An array to put the lengths of the files.

                // Read file and generate relevant information.
                File filepath = new File(infile);
                if(!filepath.exists() || filepath.isDirectory()){
                        throw new FileNotFoundException("Input file not found!");
                }

                FileReader fr = new FileReader(infile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                        line = br.readLine();
                        line = line.trim();
                        storage = storage + "," + line.length();
                        line = br.readLine();
                        line = br.readLine();
                        line = br.readLine();
                }
                br.close();
                fr.close();

                // Convert the string with the lengths into an array
                String[] tmp = storage.split(",");
                // Convert the string array to an int array
                int[] storeLen = new int[tmp.length-1];
                for(int i = 0;i < storeLen.length;i++){
                        storeLen[i] = Integer.parseInt(tmp[i+1]);
                }

                // Try out different cut-off length to make the best decision.
                Spopredopt puppy = new Spopredopt();
                int from = puppy.getMinValue(storeLen);
                int to = puppy.getMaxValue(storeLen);
                int[] base = new int[to-from+1];
                for(int j = from; j <= to; j++){
                        base[j-from] = puppy.calc(j,storeLen);
                }

                // Output the top five cut-offs that give the most number of bases
                int count=1;
                int bestInd;
                int bestVal;
                while(count<=5){
                        bestVal = puppy.getMaxValue(base);
                        bestInd = puppy.trace(bestVal, base) + from - 1;
                        System.out.println(bestInd + " : " + bestVal);
                        base[bestInd-from+1] = 0;
                        count += 1;
                }
        }
}
