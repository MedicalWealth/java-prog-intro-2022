# Задания и решения к курсу «Введение в программирование» 2022

[Условия домашних заданий](https://www.kgeorgiy.info/courses/prog-intro/homeworks.html)

## Домашнее задание 13. Обработка ошибок

Модификации
 * *Base*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [TripleParser](java/expression/exceptions/TripleParser.java)
    * Классы `CheckedAdd`, `CheckedSubtract`, `CheckedMultiply`,
        `CheckedDivide` и `CheckedNegate` должны реализовывать интерфейс
        [TripleExpression](java/expression/TripleExpression.java)
    * Нельзя использовать типы `long` и `double`
    * Нельзя использовать методы классов `Math` и `StrictMath`
    * [Исходный код тестов](java/expression/exceptions/ExceptionsTest.java)
        * Первый аргумент: `easy` или `hard`
        * Последующие аргументы: модификации
 * *SetClear* (34-37)
    * Дополнительно реализуйте бинарные операции (минимальный приоритет):
        * `set` – установка бита, `2 set 3` равно 10;
        * `clear` – сброс бита, `10 clear 3` равно 2.
 * *Count* (32, 33, 36, 37)
    * Дополнительно реализуйте унарную операцию
      `count` – число установленных битов, `count -5` равно 31.
 * *GcdLcm* (38, 39)
    * Дополнительно реализуйте бинарные операции (минимальный приоритет):
        * `gcd` – НОД, `2 gcd -3` равно 1;
        * `lcm` – НОК, `2 lcm -3` равно -6.
 * *Reverse* (38, 39)
    * Дополнительно реализуйте унарную операцию
      `reverse` – число с переставленными цифрами, `reverse -12345` равно `-54321`.
 * *PowLog10* (36-39)
    * Дополнительно реализуйте унарные операции:
        * `log10` – логарифм по уснованию 10, `log10 1000` равно 3;
        * `pow10` – 10 в степени, `pow10 4` равно 10000.


## Домашнее задание 12. Разбор выражений

Модификации
 * *Base*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [TripleParser](java/expression/parser/TripleParser.java)
    * Результат разбора должен реализовывать интерфейс
        [TripleExpression](java/expression/TripleExpression.java)
    * [Исходный код тестов](java/expression/parser/ParserTest.java)
        * Первый аргумент: `easy` или `hard`
        * Последующие аргументы: модификации
 * *SetClear* (34 - 37)
    * Дополнительно реализуйте бинарные операции (минимальный приоритет):
        * `set` – установка бита, `2 set 3` равно 10;
        * `clear` – сброс бита, `10 clear 3` равно 2.
 * *Count* (32, 33, 36, 37)
    * Дополнительно реализуйте унарную операцию
      `count` – число установленных битов, `count -5` равно 31.
 * *GcdLcm* (38, 39)
    * Дополнительно реализуйте бинарные операции (минимальный приоритет):
        * `gcd` – НОД, `2 gcd -3` равно 1;
        * `lcm` – НОК, `2 lcm -3` равно -6.
 * *Reverse* (38, 39)
    * Дополнительно реализуйте унарную операцию
      `reverse` – число с переставленными цифрами, `reverse -12345` равно `-54321`.


## Домашнее задание 11. Выражения

Модификации
 * *Base*
    * Реализуйте интерфейс [Expression](java/expression/Expression.java)
    * [Исходный код тестов](java/expression/ExpressionTest.java)
    * [Исходный код тестов для групп 32-35](java/expression/ExpressionEasyTest.java)
        * Первый аргумент: `easy` или `hard`.
        * Последующие аргументы: модификации.
 * *Triple* (32-39)
    * Дополнительно реализуйте поддержку выражений с тремя переменными: `x`, `y` и `z`.
    * Интерфейс [TripleExpression](java/expression/TripleExpression.java).
 * *Double* (36-39)
    * Дополнительно реализуйте вычисления в типе `double`.
    * Интерфейс [DoubleExpression](java/expression/DoubleExpression.java).


## Домашнее задание 10. Игра m,n,k

Тесты не предусмотрены. Решение должно находиться в пакете `game`.

Модификации
 * *Матчи* (32 - 35)
    * Добавьте поддержку матчей: последовательность игр до указанного числа побед.
    * Стороны в матче должны меняться каждую игру.
 * *Дополнительные ходы* (34, 35)
    * Если в результате хода игрока на доске появляется новая последовательность
      из 4+ одинаковых символов, то он делает дополнительный ход.
    * Игрок может сделать несколько дополнительных ходов подряд.
 * *Турнир* (36 - 39)
    * Добавьте поддержку кругового турнира для нескольких участников.
    * В рамках кругового турнира каждый с каждым должен сыграть две партии,
      по одной каждым цветом.
    * Выведите таблицу очков по схеме:
        * 3 очка за победу;
        * 1 очко за ничью;
        * 0 очков за поражение.
 * *Препятствия* (36 - 39)
    * Добавьте поддержку препятствий — клеток, в которые запрещено делать ходы.
    * В качестве примера, сделайте доску размером <em>11×11</em>,
      у которой клетки на диагоналях запрещены.
 * *Multiplayer* (38, 39)
    * Добавьте поддержку значков `-` и `|`;
    * Добавьте возможность игры для 3 и 4 игроков.




## Домашнее задание 9. Markdown to HTML

Модификации
 * *Базовая*
    * [Исходный код тестов](java/md2html/Md2HtmlTester.java)
    * [Откомпилированные тесты](artifacts/Md2HtmlTest.jar)
        * Аргументы командной строки: модификации
 * *Link* (38, 39)
    * Добавьте поддержку ```[ссылок с _выделением_](https://kgeorgiy.info)```:
        ```<a href='https://kgeorgiy.info'>ссылок с <em>выделением</em></a>```
 * *Image* (36, 37)
    * Добавьте поддержку ```![картинок](https://www.ifmo.ru/images/menu/small/p10.jpg)```:
        ```<img alt='картинок' src='https://www.ifmo.ru/images/menu/small/p10.jpg'>```
 * *Underline* (34, 35)
    * Добавьте поддержку `++подчеркивания++`: `<u>подчеркивания</u>`
 * *Mark* (32, 33)
    * Добавьте поддержку `~выделения цветом~`: `<mark>выделения цветом</mark>`


## Домашнее задание 7. Разметка

Модификации
 * *Base*
    * Исходный код тестов:
        * [MarkupTester.java](java/markup/MarkupTester.java)
        * [MarkupTest.java](java/markup/MarkupTest.java)
        * Аргументы командной строки: модификации
    * Откомпилированных тестов не существуют, 
      так как они зависят от вашего кода
 * *HtmlList* (36, 37)
    * Дополнительно реализуйте метод `toHtml`, генерирующий HTML-разметку:
      * выделеный текст окружается тегом `em`;
      * сильно выделеный текст окружается тегом `strong`;
      * зачеркнутый текст окружается тегом `s`.
    * Добавьте поддержку:
      * Нумерованных списков (класс `OrderedList`, тег `ol`): последовательность элементов
      * Ненумерованных списков (класс `UnorderedList`, тег `ul`): последовательность элементов
      * Элементов списка (класс `ListItem`, тег `li`): последовательность абзацев и списков
    * Для новых классов поддержка Markdown не требуется
    * [Исходный код тестов](java/markup/MarkupListTest.java)
 * *TexList* (38, 39)
    * Дополнительно реализуйте метод `toTex`, генерирующий TeX-разметку:
      * выделеный текст заключается в `\emph{` и `}`;
      * сильно выделеный текст заключается в `\textbf{` и `}`;
      * зачеркнутый текст заключается в `\textst{` и `}`.
    * Добавьте поддержку:
      * Нумерованных списков (класс `OrderedList`, окружение `enumerate`): последовательность элементов
      * Ненумерованных списков (класс `UnorderedList`, окружение `itemize`): последовательность элементов
      * Элементов списка (класс `ListItem`, тег `\item`: последовательность абзацев и списков
    * Для новых классов поддержка Markdown не требуется
    * [Исходный код тестов](java/markup/MarkupListTest.java)
 * *Tex* (34, 35)
    * Дополнительно реализуйте метод `toTex`, генерирующий TeX-разметку:
      * выделеный текст заключается в `\emph{` и `}`;
      * сильно выделеный текст заключается в `\textbf{` и `}`;
      * зачеркнутый текст заключается в `\textst{` и `}`.
 * *Html* (32, 33)
    * Дополнительно реализуйте метод `toHtml`, генерирующий HTML-разметку:
      * выделеный текст окружается тегом `em`;
      * сильно выделеный текст окружается тегом `strong`;
      * зачеркнутый текст окружается тегом `s`.


## Домашнее задание 6. Подсчет слов++

Модификации
 * *Base*
    * Класс должен иметь имя `Wspp`
    * Исходный код тестов:
        [WsppTest.java](java/wspp/WsppTest.java),
        [WsppTester.java](java/wspp/WsppTester.java)
    * Откомпилированные тесты: [WsppTest.jar](artifacts/WsppTest.jar)
        * Аргументы командной строки: модификации
 * *LastL* (36, 37)
    * Вместо номеров вхождений во всем файле надо указывать
      только последнее вхождение в каждой строке.
    * Класс должен иметь имя `WsppLastL`
 * *CountLastL* (38, 39)
    * В выходном файле слова должны быть упорядочены по возрастанию числа
      вхождений, а при равном числе вхождений – по порядку первого вхождения
      во входном файле.
    * Вместо номеров вхождений во всем файле надо указывать
      только последнее вхождение в каждой строке.
    * Класс должен иметь имя `WsppCountLastL`
 * *CountPosition* (34, 35)
    * В выходном файле слова должны быть упорядочены по возрастанию числа
      вхождений, а при равном числе вхождений – по порядку первого вхождения
      во входном файле.
    * Вместо номеров вхождений во всем файле надо указывать
      `<номер строки>:<номер в строке>`
    * Класс должен иметь имя `WsppCountPosition`
 * *Position* (32, 33)
    * Вместо номеров вхождений во всем файле надо указывать
      `<номер строки>:<номер в строке>`
    * Класс должен иметь имя `WsppPosition`


## Домашнее задание 5. Свой сканнер

Модификации
 * *Base*
    * Исходный код тестов: [FastReverseTest.java](java/reverse/FastReverseTest.java)
    * Откомпилированные тесты: [FastReverseTest.jar](artifacts/FastReverseTest.jar)
        * Аргументы командной строки: модификации
 * *OctDec* (36, 37)
    * На вход подаются десятичные и восьмиричные числа
    * Восьмиричные числа имеют суффикс `o`
    * Класс должен иметь имя `ReverseOctDec`
 * *OctAbc* (38, 39)
    * На вход подаются десятичные и восьмиричные числа
    * Восьмиричные числа имеют суффикс `o`
    * Десятичные числа могут быть записаны буквами:
      нулю соответствует буква `a`, единице – `b` и так далее
    * Класс должен иметь имя `ReverseOctAbc`
 * *Oct* (32, 33)
    * Во вводе и выводе используются числа в восьмеричной системе счисления
    * Класс должен иметь имя `ReverseOct`
 * *Abc* (34, 35)
    * Во вводе и выводе используются числа, записаные буквами:
      нулю соответствует буква `a`, единице – `b` и так далее
    * Класс должен иметь имя `ReverseAbc`


## Домашнее задание 4. Подсчет слов

Модификации
 * *Base*
    * Класс должен иметь имя `WordStatInput`
    * Исходный код тестов:
        [WordStatTest.java](java/wordStat/WordStatTest.java),
        [WordStatTester.java](java/wordStat/WordStatTester.java),
        [WordStatChecker.java](java/wordStat/WordStatChecker.java)
    * Откомпилированные тесты: [WordStatTest.jar](artifacts/WordStatTest.jar)
        * Аргументы командной строки: модификации
 * *WordsPrefix* (36, 37)
    * Выходной файл должен содержать все различные префиксы длины 3
      слов, встречающихся во входном файле,
      в лексикографическом порядке.
      Слова длины меньшей 3 используются как есть.
    * Класс должен иметь имя `WordStatWordsPrefix`
 * *WordsShingles* (38, 39)
    * Выходной файл должен содержать все различные подстроки длины 3
      слов, встречающихся во входном файле,
      в лексикографическом порядке.
      Слова длины меньшей 3 используются как есть.
    * Класс должен иметь имя `WordStatWordsShingles`
 * *Words* (32, 33)
    * В выходном файле слова должны быть упорядочены
      в лексикографическом порядке
    * Класс должен иметь имя `WordStatWords`
 * *WordsSuffix* (34, 35)
    * Выходной файл должен содержать все различные суффиксы длины 3
      слов встречающихся во входном файле,
      в лексикографическом порядке.
      Слова длины меньшей 3 используются как есть.
    * Класс должен иметь имя `WordStatWordsSuffix`


## Домашнее задание 3. Реверс

Модификации
 * *Base*
    * Исходный код тестов:
        [ReverseTest.java](java/reverse/ReverseTest.java),
        [ReverseTester.java](java/reverse/ReverseTester.java)
    * Откомпилированные тесты: [ReverseTest.jar](artifacts/ReverseTest.jar)
        * Аргументы командной строки: модификация
 * *Sum* (36, 37)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите сумму чисел
      в его столбце и строке
    * Класс должен иметь имя `ReverseSum`
 * *Avg* (38, 39)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите среднее из чисел в его столбце и строке
    * Класс должен иметь имя `ReverseAvg`
 * *Memory* (36-39)
    * Пусть _M_ – объём памяти, необходимый для сохранения ввода
      в двумерном массиве `int` минимального размера.
      Ваша программа должна использовать не более 4_M_ + 1024 байт памяти.
    * Накладные расходы на запуск вашей программы JVM не учитываются.
 * *Even* (32, 33)
    * Выведите (в реверсивном порядке) только четные числа
    * Класс должен иметь имя `ReverseEven`
 * *Transpose* (34, 35)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      выведите ее в транспонированном виде
    * Класс должен иметь имя `ReverseTranspose`


## Домашнее задание 2. Сумма чисел

Модификации
 * *Octal* (36, 37)
    * Восьмеричные числа имеют суффикс `o`
    * Класс должен иметь имя `SumOctal`
 * *LongOctal* (38, 39)
    * Входные данные являются 64-битными целыми числами
    * восьмеричные числа имеют суффикс `o`
    * Класс должен иметь имя `SumLongOctal`
 * *Double* (34, 35)
    * Входные данные являются 64-битными числами с формате с плавающей точкой
    * Класс должен иметь имя `SumDouble`
 * *Float* (32, 33)
    * Входные данные являются 32-битными числами с формате с плавающей точкой
    * Класс должен иметь имя `SumFloat`

Для того, чтобы протестировать исходную программу:

 1. Скачайте откомпилированные тесты ([SumTest.jar](artifacts/SumTest.jar))
 1. Откомпилируйте `Sum.java`
 1. Проверьте, что создался `Sum.class`
 1. В каталоге, в котором находится `Sum.class`, выполните команду
    ```
       java -ea -jar <путь к SumTest.jar> Base
    ```
    * Например, если `SumTest.jar` находится в текущем каталоге, выполните команду
    ```
        java -ea -jar SumTest.jar Base
    ```
 1. Для ускорени отладки рекомендуется сделать скрипт, выполняющий шаги 2−4.

Исходный код тестов:

* [SumTest.java](java/sum/SumTest.java)
* [SumTester.java](java/sum/SumTester.java)
* [Базовые классы](java/base/)


## Домашнее задание 1. Запусти меня!

Модификации
  * *RunMe*
    1. Скачайте исходный код ([RunMe.java](java/RunMe.java))
    1. Создайте скрипт, компилирующий и запускающий `RunMe` из командной строки
       с выданными вам аргументами командной строки
    1. Следуйте выведенной инструкции

Рекомендации по выполнению модификации
  1. Проверьте версию Java:
    1. Запустите `javac --version` и проверьте, что версия
       находится в диапазоне 17..18 (в крайнем случае, 11..19).
    1. Запустите `java --version` и проверьте, что версия,
       такая же как и у `javac`.
  1. Скачайте [RunMe.java](java/RunMe.java)
    1. Убедитесь, что компиляция завершилась без ошибок
  1. Откомпилируйте `RunMe.java`:
    1. Запустите `javac RunMe.java`
    1. Проверьте, что появился `RunMe.class`
  1. Запустите `RunMe`:
    1. Запустите `java RunMe [шесть] [слов] [пароля] [пришедшего] [на] [email]`
    1. При правильном исполнении вы должны получить ссылку.
       Если получено сообщение об ошибке — исправьте её и запустите повторно
    1. Зайдите по полученной ссылке и убедитесь, что она правильная
  1. Напишите и протестируйте скрипт:
    1. Напишите скрипт, включающий команды компиляции и запуска.
       Если вы не умеете писать скрипты, воспользуйтесь одной из инструкций:
       [Windows](https://www.windowscentral.com/how-create-and-run-batch-file-windows-10),
       [Linux](https://linuxhint.com/write_simple_bash_script/),
       [macOS](https://www.hastac.org/blogs/joe-cutajar/2015/04/21/how-make-simple-bash-script-mac)
    1. Запустите и проверьте, что вы получили ту же ссылку, что и в предыдущем пункте
    1. Сдайте скрипт преподавателю
  1. Вы можете получить больше плюсиков, модифицируя код `RunMe.java`
 
