# Data Types, Operators and Operations
## Task
Implement a console-based calculator that supports:
- Integer numbers (please, take into account possible overflows)
- Basic arithmetic operations (`+`, `-`, `*`, `/`. Use integer division so that `5 / 2` = 2)
- Parethesis for prioritizing certain expressions (`2 * (3 + 5)` = 16)
- Exponentiation (`3 ^ 3` = 27)

**Important!** Do not use any third-party libraries. Your calculator must be implemented using Java only.

## Format
1. User starts the application
2. net.dmytrobashynskiy.Calculator app prints the help message with supported operators
3. User enters the expression (e.g. `7 * ( 8 / (2 + 2) )`)
4. net.dmytrobashynskiy.Calculator prints the answer (14)

Possible user inputs are: 
- `4 - ( 9 + 2 )`
- `( 4 - 3 * (7 / 3))`
- `(((9)))`

Invalid user inputs are:
- `0 - 0 (/ 3`
- `4 + 4)`
- `((3)`, etc.

## Error Handling
- If user entered the wrong output (which is possible), calculator should print the `Wrong expression: <Reason>` message, where `<Reason>` is the explanation (for example, division by zero)


