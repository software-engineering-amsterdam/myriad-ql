form TestingExample {
    Name0: "Question0" integer

    if (Name0 + 1 == 3) {
        Name1: "Question1" integer (Name0 + Name2)

        if (false) {
            Name2: "Question2" integer (Name1 + 5)
        }
    }
}