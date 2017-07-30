#!/bin/bash
# Convert MAX30102 sensor output to wav file.
# Sensor output piped into stdin.
cat filtered.0 | sox -t dat -r 16000 -e floating-point -v 400.0  - t.wav
