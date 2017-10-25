#include <stdio.h>
#include "dpsim.h"

/**************************************************

Main Function:
	int main( int argc, char** argv )

------------------------------------------------
In this function perform the following steps:
------------------------------------------------
1. Create the following variables:
	- main_thread (pthread_t)
	- status (join status value)
2. Create a main_thread 
	- If the return value != 0, then display an error message and 
	  immediately exit program with status value 1.
3. Join the main_thread
	- If the return value != 0, then display an error message and
	  immediately exit the program with status value 2.
4. Display join status value.
5. Exit program.

*/

int main( int argc, char** argv ) {


	// ---------------------------------------
	// TODO: you add your implementation here
	int status = 0;
	pthread_t main_thread;

	if (pthread_create(&main_thread, NULL, (void *) th_main, NULL ) != 0){

		perror("pthread_create");
		exit(1);
	}

	if (pthread_join(main_thread, 0) != 0){
		perror("pthread_join");
		exit(2);
	}

	printf("%d\n", status);

	return 0;

} // end main function