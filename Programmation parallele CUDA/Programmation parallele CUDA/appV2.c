# include <pthread.h>
# include <stdio.h>

# define n 6
# define p 3

int **A, **B, **C;

int q = n/p;

void afficherMatrice(int **matrice ,int dim);

void *calcul(void *arg) {

	int numBloc = (int *) arg;
	int **blocA, **blocB , **blocC;

	//Allocation Memoire pour les ptrs A B C
	blocA=(int**) malloc(n * sizeof(int*) );
  	for(int i=0; i<n; i++) 
          blocA[i]=(int*)malloc(n*sizeof(int));

	blocB=(int**) malloc(n * sizeof(int*) );
  	for(int i=0; i<n; i++) 
          blocB[i]=(int*)malloc(n*sizeof(int));

    blocC=(int**) malloc(n * sizeof(int*) );
  	for(int i=0; i<n; i++) 
          blocC[i]=(int*)malloc(n*sizeof(int));

	printf("\n\n\t\t\t>>>>>>>>>>>>>>>>>je suis thread numero %d:\n" , numBloc);
  

	int indiceLigneBloc = numBloc/q , indiceColonBloc = numBloc % q;
	int indiceElementLigne , indiceElementColon;

	indiceElementLigne = ( indiceLigneBloc * p );

	for( int x=0; x<p; x++){

		 indiceElementColon = ( indiceColonBloc * p );

		for( int y=0; y<p; y++){

		 	*(blocA[x] + y) = *(A[indiceElementLigne] + indiceElementColon );
		 	*(blocB[x] + y) = *(B[indiceElementLigne] + indiceElementColon );

		 	*(C[indiceElementLigne] + indiceElementColon) = *(A[indiceElementLigne] + indiceElementColon ) 
		 													+ 
		 													*(B[indiceElementLigne] + indiceElementColon );

		 	*(blocC[x] + y) = *(A[indiceElementLigne] + indiceElementColon) 
		 						+ 
		 						*(B[indiceElementLigne] + indiceElementColon);

			indiceElementColon++;
		}
		
		indiceElementLigne++;
	}

printf("\n\t\t\t\t\t**************** voila le blocA ****************************\n");
	afficherMatrice( blocA , p);


printf("\n\t\t\t\t\t**************** voila le blocB ****************************\n");
	afficherMatrice( blocB , p);

printf("\n\t\t\t\t\t**************** voila la somme de ces 2 blocs ****************************\n");
	afficherMatrice( blocC , p);
 

}


int main(){
	printf("\n*************************************************** Mini-Projet1: Threads V2 ***************************************************\n\n");


	//Allocation Memoire pour les ptrs A B C
	A=(int**) malloc(n * sizeof(int*) );
  	for(int i=0; i<n; i++) 
          A[i]=(int*)malloc(n*sizeof(int));

	B=(int**) malloc(n * sizeof(int*) );
  	for(int i=0; i<n; i++) 
          B[i]=(int*)malloc(n*sizeof(int));

    C=(int**) malloc(n * sizeof(int*) );
  	for(int i=0; i<n; i++) 
          C[i]=(int*)malloc(n*sizeof(int));


	printf("\n\t\t\t--->Alors on a maintenant:\n"); 
	printf("\n\t\t\t\ttaille des matrices(nxn) = %dx%d\n",n,n); 
	printf("\n\t\t\t\tq^2(nombre des blocs et de threads) = %d\n",q*q); 
	printf("\n\t\t\t\ttaille de chaque bloc(pxp) = %dx%d\n", p,p); 

	printf("\n\t\t*)Initialisation des matrices A et B...");

						
			//int k = 0;

			//initialiser A
			for(int i=0; i<n; i++){

				for (int j = 0; j < n; j++){
					
					//k ++;
					*(A[i]+j) = rand() % 500;//entre 0 & 499

				}
			}	

			//initialiser B
			for(int i=0; i<n; i++){

				for (int j = 0; j < n; j++){
					
					//k++;
					*(B[i]+j) = rand() % 500;				

				}
			}	

			printf("\n\t\t\t--->Initialisation automatiques des matrices A et B est terminé.\n");

			printf("\n\t\t*)Voila la matrice A:\n");

			afficherMatrice( A , n);

		  printf("\n\t\t------------------------------------------------------------------------------------------------------\n");

		  printf("\n\t\t*)Voila la matrice B:\n");

		  afficherMatrice( B , n);



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

			afficherMatrice( C , n);

}

//Affichage des matrices  
void afficherMatrice(int **matrice ,int dim){

    for(int i=0;i<dim;i++){

    	printf("\t\t\t\t\t\t\t");
        
        for(int j=0;j<dim;j++){

            printf("%d\t", *(matrice[i] + j) );

        }

        printf("\n");
    }
}