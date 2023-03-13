#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <time.h>

#define STB_IMAGE_WRITE_IMPLEMENTATION

#include "stb_image/stb_image_write.h"

void calculatePixel(float px, float py, unsigned char *pixel, int width, int height);

void coolPixelColoring(unsigned char *pixel, int n);

float transformX(float px, int width);

float transformY(float py, int height);

int MAX_ITERATIONS = 100;

float X_MIN = -2.0f;
float X_MAX = 1.0f;

float Y_MIN = -1.5f;
float Y_MAX = 1.5f;

int main(int argc, char **argv) {
    if (argc != 3) {
        printf("Start the program with width and height parameters!\n");
        return 1;
    }

    char *p;
    int width = strtol(argv[1], &p, 10), height = strtol(argv[2], &p, 10);
    printf("Generating image with width: %d and height: %d.\n", width, height);

    unsigned char *img = malloc(width * height * 3);

    clock_t begin = clock();

    for (int x = 0; x < width; x++) {
#pragma omp parallel for
        for (int y = 0; y < height; y++) {
            unsigned char *pixel = img + (x + width * y) * 3;
            calculatePixel((float) x, (float) y, pixel, width, height);
        }
    }

    clock_t end = clock();
    printf("Image generation took %.3f seconds.", (double) (end - begin) / CLOCKS_PER_SEC);

    stbi_write_jpg("../out/test.jpg", width, height, 3, img, 100);
    free(img);

    return 0;
}

void calculatePixel(float px, float py, unsigned char *pixel, int width, int height) {

    float cx = transformX(px, width);
    float cy = transformY(py, height);

    float zx = cx;
    float zy = cy;

    for (int n = 0; n < MAX_ITERATIONS; n++) {
        float x = (zx * zx - zy * zy) + cx;
        float y = (zy * zx + zx * zy) + cy;

        if ((x * x + y * y) > 4) {
            coolPixelColoring(pixel, n);
            return;
        }
        zx = x;
        zy = y;
    }
}

void coolPixelColoring(unsigned char *pixel, int n) {
    pixel[0] = n;
    pixel[1] = n;
    pixel[2] = n;
}

float transformX(float px, int width) {
    return (px / ((float) width / (X_MAX - X_MIN)) + X_MIN);
}

float transformY(float py, int height) {
    return (py / ((float) height / (Y_MIN - Y_MAX)) + Y_MAX);
}