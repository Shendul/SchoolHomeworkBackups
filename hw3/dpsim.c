#include "dpsim.h"


static const unsigned int NUM_PHILOSPHERS = 5;
static const unsigned int NUM_CHOPSTICKS = 5;

static int chopsticks[5];
static pthread_mutex_t mutex[5];
static pthread_t philosphers[5];


void* th_main( void* th_main_args ) {

	// ---------------------------------------
	// TODO: you add your implementation here

	// 1. Initialize all element values in the chopsticks array to -1
	for(int chop = 0; chop < NUM_CHOPSTICKS; chop++){
		chopsticks[chop] = -1;
	}

	// 2. Create a thread for each philosopher (hint: use philosophers[5] array)
    // - If a error condition occurs, then immediately exit this function with status value 1.
    for (long id = 0; id < NUM_PHILOSPHERS; id++ ) {
		if (pthread_create(&philosphers[id], NULL, (void *) th_phil, (void *) id ) != 0){

		perror("pthread_create");
		exit(1);
		}
	}
	
	// 3. Execute a infinite loop that does the following:
	while(1){
		int allDown = 1;
		int currentChops[5];
			for (int t = 0; t < NUM_CHOPSTICKS; t++){
				currentChops[t] = chopsticks[t];
				if(currentChops[t] != -1){
					allDown = 0;
				}
				//printf("%i\n", currentChops[t]);
				delay(9000000);
			}

		if ( currentChops[0] == 0 && currentChops[1] == 1 && 
		currentChops[2] == 2 && currentChops[3] == 3 &&
		currentChops[4] == 4 ){ // If a deadlock condition is found then
			printf("Deadlock condition (0,1,2,3,4) ... terminating\n");
			// 4. Kill each philosopher thread
			/*for (int endID = 0; endID < NUM_PHILOSPHERS; endID++ ) {
				if (pthread_kill(philosphers[endID], 1) != 0){
					perror("pthread_kill");
					pthread_exit(2);
				}
			}*/

			// 5. Exit the main thread with status value equal to 0.
			pthread_exit(0);
		} else {
			// display which philosophers are eating
			if(allDown == 1){
				// dont print anything.
			} else {
				//delay(900000000);
				printf("Philopsher(s) ");
				int numEating = 0;
				int count0 = 0;
				int count1 = 0;
				int count2 = 0;
				int count3 = 0;
				int count4 = 0;


				for (int i = 0; i < NUM_CHOPSTICKS; i++ ){
					if (currentChops[i] == 0){
						count0 += 1;
					}
					if (currentChops[i] == 1){
						count1 += 1;
					}
					if (currentChops[i] == 2){
						count2 += 1;
					}
					if (currentChops[i] == 3){
						count3 += 1;
					}
					if (currentChops[i] == 4){
						count4 += 1;
					}
				}

				if( count0 == 2){
					numEating += 1;
				}
				if( count1 == 2){
					numEating += 1;
				}
				if( count2 == 2){
					numEating += 1;
				}
				if( count3 == 2){
					numEating += 1;
				}
				if( count4 == 2){
					numEating += 1;
				}


				if (count0 == 2 && numEating == 2){
					printf("0, ");
					numEating = 0;
				} else if (count0 == 2) {
					printf("0 ");
				}
				if (count1 == 2 && numEating == 2){
					printf("1, ");
					numEating = 0;
				} else if (count1 == 2) {
					printf("1 ");
				}
				if (count2 == 2 && numEating == 2){
					printf("2, ");
					numEating = 0;
				} else if (count2 == 2) {
					printf("2 ");
				}
				if (count3 == 2 && numEating == 2){
					printf("3, ");
					numEating = 0;
				} else if (count3 == 2) {
					printf("3 ");
				}
				if (count4 == 2 && numEating == 2){
					printf("4, ");
					numEating = 0;
				} else if (count4 == 2) {
					printf("4 ");
				}

				printf("are eating\n");
		 		// and then call the delay function (you specify nanosec sleep value)
		 		delay(400000000);
		 	}
		}
	} // end loop

} // end th_main function


void* th_phil( void* th_phil_args ) {

	// ---------------------------------------
	// TODO: you add your implementation here

	// 1. Get the philosopher id (hint: use th_phil_args)
	long phil_id = (long) th_phil_args;

	//  2. Execute an infinite loop that does the following:
	while(1){
		//printf("phil thread %ld looping\n", phil_id);
 	// - call the delay function for thinking (you specify nanosec sleep value)
		delay(5000000);
 	// - call the eat function (argument is the philosopher id)
		eat(phil_id);
	}

} // end th_phil function


void delay( long nanosec ) {

	struct timespec t_spec;

	t_spec.tv_sec = 0;
	t_spec.tv_nsec = nanosec;

	nanosleep( &t_spec, NULL );

} // end think function


void eat( int phil_id ) {

	// ---------------------------------------
	// TODO: you add your implementation here
	/* This philosopher, phil_id, picks up chopstick phil_id
		 (i.e. the right chopstick) then delays for no more than 
		 20,000 nanoseconds. Next the philosopher picks up the 
		 left chopstick. Note: mutual exclusion is necessary when 
		 updating a shared resource. After having picked up both 
		 currentChops (as described) the philosopher will delay a 
		 number of nanoseconds that is determined by you experimentally.
		 After the delay completes, the philosopher will release 
		 the two chopsticks in the reverse order in which they were
		 picked up. */
		if (phil_id == 4){
			pthread_mutex_lock( &mutex[phil_id] );
			chopsticks[phil_id] = phil_id;
			delay(12000);
			pthread_mutex_lock( &mutex[0] );
			chopsticks[0] = phil_id;
			delay(300000000);
			chopsticks[0] = -1;
			pthread_mutex_unlock( &mutex[0] );
			chopsticks[phil_id] = -1;
			pthread_mutex_unlock( &mutex[phil_id] );
		} else {
			pthread_mutex_lock( &mutex[phil_id] );
			chopsticks[phil_id] = phil_id;
			delay(17000);
			pthread_mutex_lock( &mutex[phil_id + 1] );
			chopsticks[phil_id + 1] = phil_id;
			delay(600000000);
			chopsticks[phil_id + 1] = -1;
			pthread_mutex_unlock( &mutex[phil_id + 1] );
			chopsticks[phil_id] = -1;
			pthread_mutex_unlock( &mutex[phil_id] );
		}



} // end eat function
