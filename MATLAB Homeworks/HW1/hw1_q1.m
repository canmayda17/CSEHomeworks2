% Şükrü Can Mayda - 150120031

clc;
clear;

% Given parametric equations
t = 0:1/16:100;
x=sin(t).* ((exp(1).^(cos(t))- 2*cos(4*t) - (sin(t/12)).^5));
y=cos(t).* ((exp(1).^(cos(t))- 2*cos(4*t) - (sin(t/12)).^5));

A = subplot(2,1,1);
% x versus t
plot(t,x,'g');
% to keep plot
hold on;
% y versus t
plot (t,y,'-.b');
% name of axises
xlabel('t')
ylabel('x(green) and y(blue)')
% name of title
title('2D Plot of x and y versus t')
legend('=x','=y');

B = subplot(2,1,2);
% x versus y
plot (x,y)
xlabel('x')
ylabel('y');
title('2D Plot of y versus x');
% do subplot aquare
pbaspect([1 1 1]);
% it is for most consistent graphic
axis([-4 4 -3 4])