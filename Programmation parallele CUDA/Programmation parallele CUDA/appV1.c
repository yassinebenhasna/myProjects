# include <pthread.h>
# include <stdio.h>

# define n 6
# define p 3

int A[n][n], B[n][n], C[n][n];
int q = n/p;

void *calcul(void *arg) {

	int numBloc = (int *) arg;
	int blocA[p][p] , blocB[p][p], blocC[p][p];

	printf("\n\n\t\t\t>>>>>>>>>>>>>>>>>je suis thread numero %d:\n" , numBloc);
  

	int indiceLigneBloc = numBloc/q , indiceColonBloc = numBloc % q;
	int indiceElementLigne , indiceElementColon;

	indiceElementLigne = ( indiceLigneBloc * p );

	for( int x=0; x<p; x++){

		 indiceElementColon = ( indiceColonBloc * p );

		for( int y=0; y<p; y++){

		 	blocA[x][y] = A[indiceElementLigne][indiceElementColon];
		 	blocB[x][y] = B[indiceElementLigne][indiceElementColon];
		 	C[indiceElementLigne][indiceElementColon] = A[indiceElementLigne][indiceElementColon] + B[indiceElementLigne][indiceElementColon];
		 	blocC[x][y] = A[indiceElementLigne][indiceElementColon] + B[indiceElementLigne][indiceElementColon];

			indiceElementColon++;
		}
		
		indiceElementLigne++;
	}

printf("\n\t\t\t\t\t**************** voila le blocA ****************************\n");

  for (int i=0; i<p; i++){

  		printf("\t\t\t\t\t\t\t");
    
    for (int j=0; j<p; j++){

      printf(" %d \t" , blocA[i][j]);
    
    }

    printf("\n");
  }

printf("\n\t\t\t\t\t**************** voila le blocB ****************************\n");

  for (int i=0; i<p; i++){

  		printf("\t\t\t\t\t\t\t");
    
    for (int j=0; j<p; j++){

      printf(" %d \t" , blocB[i][j]);
    
    }

    printf("\n");
  }

printf("\n\t\t\t\t\t**************** voila la somme de ces 2 blocs ****************************\n");

  for (int i=0; i<p; i++){

  		printf("\t\t\t\t\t\t\t");
    
    for (int j=0; j<p; j++){

      printf(" %d \t" , blocC[i][j]);
    
    }

    printf("\n");
  }


}


int main(){
	printf("\n*************************************************** Mini-Projet1: Threads V1 ***************************************************\n\n");


	printf("\n\t\t\t--->Alors on a maintenant:\n"); 
	printf("\n\t\t\t\ttaille des matrices(nxn) = %dx%d\n",n,n); 
	printf("\n\t\t\t\tq^2(nombre des blocs et de threads) = %d\n",q*q); 
	printf("\n\t\t\t\ttaille de chaque bloc(pxp) = %dx%d\n", p,p); 

	printf("\n\t\t*)Initialisation des matrices A et B...");

						
			//initialiser A
			for(int i=0; i<n; i++){

				for (int j = 0; j < n; j++){
					
					//k ++;
					A[i][j] = rand() % 500;					

				}
			}	

			//initialiser B
			for(int i=0; i<n; i++){

				for (int j = 0; j < n; j++){
					
					//k++;
					B[i][j] = rand() % 500;					

				}
			}	

			printf("\n\t\t\t--->Initialisation automatiques des matrices A et B est terminé.\n");

			printf("\n\t\t*)Voila la matrice A:\n");

			for (int i=0; i<n; i++){

				printf("\t\t\t\t\t\t\t");

			    for (int j=0; j<n; j++){

			      printf(" %d \t" , A[i][j]);
			    
			    }

		    printf("\n");
		  }

		  printf("\n\t\t------------------------------------------------------------------------------------------------------\n");

		  printf("\n\t\t*)Voila la matrice B:\n");
		  
			for (int i=0; i<n; i++){

				printf("\t\t\t\t\t\t\t");

			    for (int j=0; j<n; j++){

			      printf(" %d \t" , B[i][j]);
			    
			    }

		    printf("\n");
		  }



	printf("\n\t\t*)on va créer %d threads maintenant...\n", q*q);
			
			//creer les threads 
			int nbrThreads = q*q;
			pthread_t thread[nbrThreads];


			for (int i=0; i<nbrThreads; i++){
				
				int numBloc = i;

				

				pthread_create(&thread[i], NULL, calcul, (void *) numBloc);
				sleep(2);
			}

			for (int i=0; i<nbrThreads; i++){
				pthread_join ( thread[i] , NULL );
			}


			printf("\n\n\n\t\t---------------------------------------------- la matrice final C:----------------------------------------------\n");

			for (int i=0; i<n; i++){

				printf("\t\t\t\t\t\t\t");

			    for (int j=0; j<n; j++){

			      printf(" %d \t" , C[i][j]);
			    
			    }

		    printf("\n");
		  }
		  
}