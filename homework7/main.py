import os
import shlex
import subprocess
from kazoo.client import KazooClient

previous_children = []
process = None

zk = KazooClient(hosts="127.0.0.1:2182")
zk.start()

@zk.ChildrenWatch("/")
def watch_root(children):
    global previous_children, process
    if "z" not in previous_children and "z" in children:
        args = shlex.split("spotify")
        process = subprocess.Popen(args)

        @zk.ChildrenWatch("/z")
        def watch_z(children):
            print(f"z has {len(children)} children")

    elif "z" in previous_children and "z" not in children:
        if process != None:
            process.kill()
            process = None

    previous_children = children



def display_tree(name: str) -> None:
    print(name)
    if zk.exists(name):
        children = zk.get_children(name)

        if children:
            for child in children:
                child_name = name + "/" + child

                display_tree(child_name)

while True:
    command = input(" >>> ")

    if command in ["q", "quit"]:
        break

    if command == "tree":
        if zk.exists("/z"):
            display_tree("z")
        else:
            print("z doesn't exist")

zk.stop()
