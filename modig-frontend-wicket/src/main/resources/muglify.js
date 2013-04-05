function muglify(code) {
    var config = {
        mangle: true,
        squeeze: true,
        semicolon: false,
        lift_vars: true,
        mangle_toplevel: true,
        no_mangle_functions: true,
        max_line_length: 6000,
        aschii_only: true

    };


    var ast = parse(code);

    ast = ast_mangle(ast, config);
    ast = ast_squeeze(ast, config);
    code = gen_code(ast, config);
    code = split_lines(code, config.max_line_length);

    if ((code.substr(code.length - 1) === ')') || code.substr(code.length - 1) === '}') {
        code += ';'
    }
    code += '\n';
    return code;
//    return "";
}