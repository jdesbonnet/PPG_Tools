#include <stdio.h>
#include <time.h>

/**
 * Time tag incoming data from UART or other sources. 
 * Note that timestamp applies after line is received. 
 * Event though time is in microsecond resolution, it is
 * only accurate to milliseconds. 
 *
 * TODO: look into using time that first character is
 * received.
 */
int main (int argc, char **argv) {

    char buffer[4096];
    struct timespec ts;

    while (fgets(buffer,4096,stdin)) {

        clock_gettime(CLOCK_REALTIME, &ts);

	// Write unix epoch time in seconds with microsecond resolution
        printf ("%d.%06d ", (int)ts.tv_sec, (int)(ts.tv_nsec/1000));

        fputs(buffer,stdout);
    }
}

