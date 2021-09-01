def mergeSort(a, b, na, nb, c):
    curserA = 0
    curserB = 0
    # curserC = 0

    while curserA < na and curserB < nb:
        valA = a[curserA]
        valB = b[curserB]
        minN = min(valA, valB)
        c.append(minN)

        if valA == valB:
            curserA += 1
        else:
            if valA == minN:
                curserA += 1
            if valB == minN:
                curserB += 1

    while curserA < na:
        c.append(a[curserA])
        curserA += 1

    while curserB < na:
        c.append(b[curserB])
        curserB += 1



def mergeSort2(a, n):

    def __mergeSort(arr, left, right):

        tmptArr = [0,0,0,0,0,0,0,0,]

        if left < right:
            center = int((left+right)/2)
            __mergeSort(arr, left, center)
            __mergeSort(arr, center+1, right)

            curserArr2 = 0
            tmptLen = 0
            for i in range(left, center+1):
                curserArr2 = i
                tmptArr[tmptLen] = arr[i]

            curserArr2 += 1
            curserT = 0
            curserArr = left

            while curserArr2 <= right and curserT < tmptLen:
                valA = arr[curserArr2]
                valT = tmptArr[curserT]
                valM = min(valA, valT)
                arr[curserArr] = valM
                curserArr+=1

                curserArr2 = curserArr2+1 if valA!=valT and valM==valA else curserArr2
                curserT = curserT+1 if valA==valT or valM==valT else curserT

            while curserT < tmptLen:
                arr[curserArr] = tmptArr[curserT]
                curserArr+=1
                curserT+=1

    __mergeSort(a, 0, n-1)


a = [2,4,5]
b = [1,2,3,6]
c = []

a = [1,3,1]
# mergeSort(a,b,3,3,c)
mergeSort2(a,7)
print(a)