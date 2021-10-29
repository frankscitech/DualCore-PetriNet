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
    pattern='(T0)(.*?)((T1)(.*?)((T5)(.*?)(T7)(.*?)(T3)(.*?)(T4)(.*?)(T8)(.*?)|(T6)(.*?)(T3)(.*?)(T4)(.*?))|(T2)(.*?)((T11)(.*?)(T12)(.*?)(T9)(.*?)(T10)(.*?)(T14)(.*?)|(T13)(.*?)(T9)(.*?)(T10)(.*?)))'
    repl='[removed]'
    string=readFile(filename)
    line = re.subn(pattern, repl, string)
    print(string)
    print(line)

