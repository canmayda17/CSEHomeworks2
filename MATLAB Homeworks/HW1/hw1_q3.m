% Şükrü Can Mayda - 150120031

clc;
clear;

x = input('x : '); % Number of terms
n = input('n : '); % Percent relative error

sinn = 0; % sin value
i = 1;

sign = 0;
fact = 1; % Factoriel

numberOfTerms = 0;
errors = []; % Error counter
counter = 1;

while (i <= n)
    if (i > 1)
        % Factoriel formula
        fact = fact * i *(i-1);
    end
    % sin value
    sinn = sinn + (-1).^sign * (x^i)/fact;
    % error value
    err = (sin(x) - sinn)/sin(x) * 100; 
    
    errors(counter) = err;
    
    i = i+2;
    sign = sign + 1;
    numberOfTerms = numberOfTerms + 1;
    counter = counter + 1;
end

y = 1:1:numberOfTerms; % raise by 1 from 1 to number of terms

plot(y,errors,'r.--')
xlabel('Number of terms');
ylabel('Percent relative error (%)');
title('Percent relative error versus Number of terms')