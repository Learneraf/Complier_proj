toFahrenheit <- function(celsius) {
    return (celsius * 9 / 5 + 32)
}

temp <- 25
f <- toFahrenheit(temp)

if (f > 80) 
    print(1) 
else 
    print(0)
