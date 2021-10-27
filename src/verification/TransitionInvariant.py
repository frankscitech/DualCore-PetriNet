import re
''' 
    param: name of file
    return: disparos as string 
    e.g:
''' 
def readFile(filename):
    disparos=''
    with open(filename, 'rt') as f:
        data = f.readlines()
    for line in data:
        if line.__contains__('disparo'):
            line_arr=line.split('=')
            disparo=int(line_arr[1])
            disparos+='T'+str(disparo)
    return disparos

if __name__ == "__main__":
    filename='../../log.txt'
    pattern = '(T0)(.*?)((T1)(.*?)(T5)(.*?)(T7)(.*?)(T3)(.*?)(T4)(.*?)(T8)(.*?)|(T2)(.*?)(T11)(.*?)(T12)(.*?)(T9)(.*?)(T10)(.*?)(T14)(.*?))'
    #repl='\g<2>\g<5>\g<7>\g<9>\g<1>1\g<1>3\g<1>5\g<1>7\g<1>9\g<2>1\g<2>3\g<2>5\g<2>7'
    #repl='\g<2>\g<5>\g<7>\g<9>\g<11>\g<13>\g<15>\g<17>\g<19>\g<21>\g<23>\g<25>\g<27>'
    repl='[removed]'
    string=readFile(filename)
    line = re.subn(pattern, repl, string)
    print(string)
    print(line)


'''
    re.subn(pattern, repl, string, count=0, flags=0)

    brief:  Return the string obtained by replacing the leftmost non-overlapping occurrences of pattern 
            in string by the replacement repl. If the pattern isn’t found, string is returned unchanged.
    return: a tuple (new_string, number_of_subs_made).
'''
