import numpy as np
import ray
import cProfile

# Excercises 2.1) Create large lists and python dictionaries,
# put them in object store. Write a Ray task to process them.

array = np.random.rand(10**6)

array_ref = ray.put(array)


@ray.remote
def sum_and_avarage(array):
    return np.sum(array), np.average(array)


def bench():
    result_sum, result_avg = ray.get(sum_and_avarage.remote(array_ref))


cProfile.run("bench()")

ray.shutdown()
