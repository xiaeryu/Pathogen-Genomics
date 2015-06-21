Pathogen Genomics
=================
 In this repository are my scripts written for pathogen genomics research.

* **Spopredopt.java**  
  A scipt for paremeter optimization of _SpoPred_.  
  _SpoPred_ is a software for _in silico_ spoligotyping of _Mycobacterium tuberculosis_ developed by Coll F, et al.
  * Coll F, Mallard K, Preston M D, et al. SpolPred: rapid and accurate prediction of Mycobacterium tuberculosis spoligotypes from short genomic sequences[J]. Bioinformatics, 2012, 28(22): 2991-2993.

  A flaw of _SpoPred_ is the parameter selection for reads of varying lengths. If the read length is set too low, then the information after the specified read length will be lost. If, however, the read length is set too high, the information in the short reads would be lost. This script helps to get the read length that preserves the most number of bases. It will output the top five read lengths that give the most number of bases preserved. Take note that processing all the reads for parameter optimization can be time-consuming with this script. It's suggested to take the top 100000 reads to estimate the best read length, which is fine due to the random nature of the read length distribution in a sequencing run.
