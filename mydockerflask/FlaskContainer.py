from flask import Flask
from flask import jsonify


app = Flask (__name__)

@app.route('/')
def home():
    import docker
    cList=[]
    client = docker.from_env()
    for container in client.containers.list():
        cList.append(container.id)
    return jsonify(cList)


if __name__ == '__main__':
    app.run(host="0.0.0.0")