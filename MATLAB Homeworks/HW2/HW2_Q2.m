
clc; clear;

k = 1:10;
X = 10 .^ -k;
Y = X .*(((2./X + X) - X) - 1./X)