% Şükrü Can Mayda - 150120031

clc;
clear;

% given theta
theta = 0:pi/32:8*pi;

% radius according to theta
r = exp(1).^sin(theta) - 2*cos(4*theta) - sin((2*theta - pi)/24).^5;

% do polar graphic
polar(theta,r,'--r');
title('Plot of r and theta');