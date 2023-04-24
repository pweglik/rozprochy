import ray
import numpy as np
import os

# Excercises 1.1)Try using local bubble sort and remote bubble sort,
# show difference


def bubble_sort(array: np.array) -> np.array:
    copy = np.copy(array)
    # loop to access each array element
    for i in range(len(array)):
        # loop to compare array elements
        for j in range(0, len(array) - i - 1):
            # compare two adjacent elements
            # change > to < to sort in descending order
            if copy[j] > copy[j + 1]:
                # swapping elements if elements
                # are not in the intended order
                temp = copy[j]
                copy[j] = copy[j + 1]
                copy[j + 1] = temp

    return copy


@ray.remote
def bubble_sort_distributed(array):
    return bubble_sort(array)


# Get the number of cores
print(f"Cores: {os.cpu_count()}")


# Normal Python in a single process
def run_local(n):
    array = np.random.rand(n)
    results = [bubble_sort(array) for _ in range(os.cpu_count())]
    return results


import cProfile

print("local run")
cProfile.run("run_local(4000)")


# Distributed on a Ray cluster
def run_remote(n):
    array = np.random.rand(n)
    futures = [bubble_sort_distributed.remote(array) for _ in range(os.cpu_count())]

    results = [ray.get(future) for future in futures]

    return results


print("remote run")
cProfile.run("run_remote(4000)")

ray.shutdown()
