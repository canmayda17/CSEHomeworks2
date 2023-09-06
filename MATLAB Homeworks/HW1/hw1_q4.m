% Şükrü Can Mayda - 150120031

clc;
clear;

% Given equations

%[minf, maxf]=minAndMax(@(t) 8*exp(-0.25*t).*sin(t-2), 0, 6*pi); % a
%[minf, maxf]=minAndMax(@(x) exp(4*x).*sin(1./x), 0.01, 0.2); % b
%[minf, maxf]=minAndMax(@(t) humps(t), 0, 2); % c


function[minf,maxf] = minAndMax(f,x,y)

% x value
xAxis = linspace(x,y,max(abs(x),abs(y))*1000);
% y value according to f(x)
yAxis = f(xAxis);

% min value
minf = min(yAxis);
% max value
maxf = max(yAxis);

% plot the graph
plot(xAxis,yAxis);
% axis limits
axis([x y minf maxf]);
xlabel('x values for the given range');
ylabel('y values for the given range');
title('Plot of the function');

end