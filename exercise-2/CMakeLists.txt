cmake_minimum_required(VERSION 3.24)
project(exercise_2 C)

set(CMAKE_C_STANDARD 99)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fopenmp")

add_executable(exercise_2 main.c)
