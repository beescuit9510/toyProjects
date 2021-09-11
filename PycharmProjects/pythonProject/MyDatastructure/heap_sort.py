def down_heap(array :[], left:int, right:int):
    temp_left_node = array[left]

    parent = left

    while parent < (right+1)/2:
        child_left = parent*2+1
        child_right = child_left+1

        # print("parent : ",parent," c left",child_left," c right:",child_right," right: ", right)

        if child_right >= right:
            break

        condition_1 = array[child_left] < array[child_right]

        child = child_right if condition_1 else child_left

        if array[child] <= temp_left_node:
            break

        array[parent] = array[child]

        parent = child

    array[parent] = temp_left_node


def heap_sort(array:[], length:int):

    idx = int((length-1)/2)
    while idx >= 0:
        down_heap(array, idx, length)
        idx-=1

    idx = length - 1
    while idx > 0:
        array[0], array[idx] = array[idx], array[0]
        down_heap(array, 0, idx-1)
        idx-=1




arr = [1,9,82,4,1,5,2]

heap_sort(arr, len(arr))
print(arr)

