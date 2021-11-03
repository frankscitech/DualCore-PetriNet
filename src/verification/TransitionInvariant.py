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
    filename='./log.txt'
    pattern = '(T0)(.*?)((T1)(.*?)(T5)(.*?)(T7)(.*?)(T3)(.*?)(T4)(.*?)(T8)(.*?)|(T2)(.*?)(T11)(.*?)(T12)(.*?)(T9)(.*?)(T10)(.*?)(T14)(.*?))'
    #pattern='((T0)(.*?)((T1)(.*?)(T5)(.*?)(T7)(.*?)(T3)(.*?)(T4)(.*?)(T8)(.*?)|(T2)(.*?)(T11)(.*?)(T12)(.*?)(T9)(.*?)(T10)(.*?)(T14)(.*?)))|(T2)(.*?)(T11)(.*?)(T12)(.*?)(T9)(.*?)'
    #pattern='(T0)(.*?)((T1)(.*?)((T5)(.*?)(T7)(.*?)(T3)(.*?)(T4)(.*?)(T8)(.*?)|(T6)(.*?)(T3)(.*?)(T4)(.*?))|(T2)(.*?)((T11)(.*?)(T12)(.*?)(T9)(.*?)(T10)(.*?)(T14)(.*?)|(T13)(.*?)(T9)(.*?)(T10)(.*?)))'
    repl=' '
    string=readFile(filename)
    #string='T0T2T11T12T9T0T2T13T0T2T13T0T2T13T10T9T0T2T13T10T9T0T1T5T7T3T10T9T10T9T0T1T6T4T3T4T8T10T14T0T2T11T12T9T10T14T0T2T11T12T9T10T14T0T2T11T12T9T0T1T5T7T3T4T8T10T14T0T2T11T12T9T10T14T0T1T5T7T3T4T8T0T1T5T7T3T4T8T0T1T5T7T3T4T8T0T2T11T12T9T10T14'
    line = re.subn(pattern, repl, string)
    print(string)
    print(line)


'''
    re.subn(pattern, repl, string, count=0, flags=0)

    brief:  Return the string obtained by replacing the leftmost non-overlapping occurrences of pattern 
            in string by the replacement repl. If the pattern isn’t found, string is returned unchanged.
    return: a tuple (new_string, number_of_subs_made).


   '(T0)(.*?)
       (
       (T1)(.*?)(T5)(.*?)(T7)(.*?)(T3)(.*?)(T4)(.*?)(T8)(.*?)
       |
       (T2)(.*?)
            (
            (T11)(.*?)(T12)(.*?)(T9)(.*?)(T10)(.*?)(T14)(.*?)
            |
            (T9)(.*?)(T10)(.*?)(T13)(.*?)(T10)
            )
       ) 
'''
