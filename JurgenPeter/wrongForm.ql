form BuggyForm {
    x: "x1?" boolean = y
    x: "x2?" boolean = y

    y: "y?" boolean = x
    z: "z" integer

    if z >= 10 {
        aa: "a" decimal
        ab: "a" decimal
        b: "b" decimal
    }
    if 2 {
        d: "d" string = "d" + 2
    }

    e: "e" integer = true

    if f {
        g: "g" integer = g + 1
    }

    if h {
        h: "h" boolean
    }
}
