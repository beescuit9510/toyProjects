def quickSort(arr, left, right):
    pl = left
    pr = right
    pivot = arr[int((left+right)/2)]


    plBiggerThanPr = True
    while plBiggerThanPr:
        while arr[pl] < pivot :
            pl+=1
        while arr[pr] > pivot:
            pr-=1
        if pl<=pr :
            swap(arr,pl,pr)
            pl+=1
            pr-=1
        plBiggerThanPr = pl <= pr

    if left < pr:
        quickSort(arr, left, pr)

    if pl < right:
        quickSort(arr, pl, right)

def swap(arr, a, b):
    arr[a], arr[b] = arr[b], arr[a]


arr = [6,1,8,7,3,2]

quickSort(arr, 0, len(arr)-1)
print(arr)