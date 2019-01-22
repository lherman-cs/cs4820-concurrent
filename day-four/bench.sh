#!/bin/bash

MIN_CHUNK_SIZES=(32 16 8 4 2 1)
PROG="TestThread"

for min_chunk_size in ${MIN_CHUNK_SIZES[@]}
do
  echo "MIN_CHUNK_SIZE: ${min_chunk_size}"
  # generate it first
  sed "s/{MIN_CHUNK_SIZE}/${min_chunk_size}/" "${PROG}.java.tmpl" > ${PROG}.java
  # compile it first
  javac "${PROG}.java"
  # time it
  time java ${PROG} > /dev/null

  printf "\n\n"
done