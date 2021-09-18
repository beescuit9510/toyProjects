def countring_sort(arr):
    count = [0 for i in range(max(arr)+1)]
    accumulated = [0 for i in range(max(arr)+1)]
    output = [0 for i in range(len(arr))]

    for i in arr:
        count[i] += 1

    accumulated[0] = count[0]
    for i in range(1, len(accumulated)):
        accumulated[i] = count[i] + accumulated[i-1]

    arr.reverse()
    for i in arr:
        output[accumulated[i]-1] = i
        accumulated[i]-=1

    return output


a = [2,3,1,5,8,1,3,11]
b = countring_sort(a)
print(b)





