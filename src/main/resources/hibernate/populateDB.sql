
INSERT IGNORE INTO `USER`(`user_Id`, `user_name`, `password`, `access`, `status`, `rating_RATING_ID`)
VALUES (01, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, 1, null);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (01, 'Which method must be defined by a class implementing the java.lang.Runnable interface?',
'void run()', 'public void run()', 'public void start()', 'void run(int priority)', 2);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (02, 'Which of the following will not directly cause a thread to stop?',
'notify()', 'wait()', 'InputStream access', 'sleep()', 3);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (03, 'Which collection class allows you to grow or shrink its size and provides indexed access to its elements, but whose methods are not synchronized?',
'java.util.HashSet', 'java.util.LinkedHashSet', 'java.util.List', 'java.util.ArrayList', 4);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (04, 'Which statement is true?',
'Memory is reclaimed by calling Runtime.gc().', 'Objects are not collected if they are accessible from live threads.',
'An OutOfMemory error is only thrown if a single block of memory cannot be found that is large enough for a particular requirement.',
'Objects that have finalize() methods always have their finalize() methods called before the program ends.', 2);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (05, 'Which is a valid declaration within an interface?',
'public static short stop = 23;', 'protected short stop = 23;', 'transient short stop = 23;',
'final void madness(short stop);', 1);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (06, 'Which is true about an anonymous inner class?',
'It can extend exactly one class and implement exactly one interface.', 'It can extend exactly one class and can implement multiple interfaces.',
'It can extend exactly one class or implement exactly one interface.', 'It can implement multiple interfaces regardless of whether it also extends a class.', 3);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (07, 'Which interface provides the capability to store objects using a key-value pair?', 'Java.util.Map',
'Java.util.Set', 'Java.util.List', 'Java.util.Collection', 1);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (08, 'Which interface does java.util.Hashtable implement?', 'Java.util.Map', 'Java.util.List',
'Java.util.HashTable', 'Java.util.Collection', 1);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (09, 'What is the most restrictive access modifier that will allow members of one class to have access to members of another class in the same package?',
'public', 'abstract', 'protected', 'default access', 4);

INSERT IGNORE INTO `TASK`(`TASK_ID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct`)
VALUES (10, 'You want a class to have access to members of another class in the same package. Which is the most restrictive access that accomplishes this objective?',
'public', 'private', 'protected', 'default access', 4);