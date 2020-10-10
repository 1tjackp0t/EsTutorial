PUT /bloguser
{
    "mappings": {
        "dynamic":true,
        "properties": {
            "name":{
                "type":"text",
                "analyzer": "ik_max_word",
                "search_analyzer": "ik_max_word"
            },
            "hobby":{
                "type":"text",
                "analyzer": "ik_max_word",
                "search_analyzer": "ik_max_word"
            }
        }
    }
}

GET /bloguser/_mapping

POST /bloguser/_doc
{
    "name":"张三",
    "hobby":["足球","看电影"]
}

GET /bloguser/_search
{
     "query": {
        "match_all": {}
    }
}

GET /bloguser/_search
{
    "query": {
        "match_phrase_prefix": {
            "name": "张三"
        }
    }
}

DELETE /bloguser