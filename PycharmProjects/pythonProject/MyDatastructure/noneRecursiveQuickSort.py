class Stack:
    def __init__(self):
        self.arr = []

    def print(self):
        print(self.arr)

    def push(self, n):
        self.arr.append(n)

    def pop(self):
        return self.arr.pop()

    def isEmpty(self):
        return len(self.arr)==0


def quickSort(arr, left, right):

    lstack = Stack()
    rstack = Stack()
    lstack.push(left)
    rstack.push(right)

    while lstack.isEmpty()== False:
        pl = left = lstack.pop()
        pr = right = rstack.pop()
        pivot = arr[int((right+left)/2)]

        while pl<=pr:
            while arr[pl] < pivot:
                pl+=1
            while arr[pr] > pivot:
                pr-=1
            if pl<=pr:
                arr[pr], arr[pl] = arr[pl], arr[pr]
                pl+=1
                pr-=1

        if left < pr:
            lstack.push(left)
            rstack.push(pr)


        if pl < right :
            lstack.push(pl)
            rstack.push(right)



arr = [3,6,9,2]

quickSort(arr, 0, len(arr)-1)
print(arr)