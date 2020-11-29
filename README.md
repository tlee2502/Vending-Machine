VENDING MACHINE
Uses Spring framework (dependency injection XML format), JUnit testing, Maven to bring in external libraries.
Follows the MVC design architecture.

Application programmed to behave as a vending machine. Users are displayed all items, in stock and out of stock. Items are read in from data persistence (.txt file).
Users are able to enter an amount of money, choose an in stock item to purchase. The program logic will keep track and update all stock information- stock amount, price, item name.
User cannot add new items, must edit the .txt file. Program calculates the change, returns to user in denominations using the BigDecimal divideAndRemainder() method along with an enum class which associates each coin to its corresponding BigDecimal value.
A sample output of the program is included.