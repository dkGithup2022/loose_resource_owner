import random
import string
from locust import task, FastHttpUser, stats

stats.PERCENTILES_TO_CHART = [0.95, 0.99]



def random_string(length=10):
    letters = string.ascii_letters + string.digits
    return ''.join(random.choice(letters) for _ in range(length))

def build_payload():
    if(random.randint(1,2)%2 == 0):
        return  {
            "ownerType" :"USER",
            "ipv4": None,
            "userId": random.randint(1, 10000),
        }
    else:
        return  {
            "ownerType" :"IPv4",
            "ipv4": "192.168."+str(random.randint(0,100)) +"."+str(random.randint(0,100)),
            "userId": None,
        }

class SaveWithUserID(FastHttpUser):
    connection_timeout = 10.0
    network_timeout = 10.0

    @task
    def issue(self):
        payload =  build_payload()
        with self.rest("GET", "/resourceOwner/read", json=payload):
            pass