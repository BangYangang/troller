| Controller | Method | URL                                                       | Request Header              | Response                                                        | Request                                                                                        |
|------------|--------|-----------------------------------------------------------|-----------------------------|-----------------------------------------------------------------|------------------------------------------------------------------------------------------------|
| 카드 생성      | POST   | /api/v1/boards/{board_id}/todos/{todo_id}/cards           | Authorization : accessToken | { "status" : 201, "message": "카드 작성 성공" }                       | { "card_name": "text" }                                                                        |
| 카드 조회      | GET    | /api/v1/boards/{board_id}/todos/{todo_id}/cards           | Authorization : accessToken | { "status" : 200, "message": "카드 조회 성공", "data": {responseDto } |                                                                                                |
| 카드 삭제      | DELETE | /api/v1/boards/{board_id}/todos/{todo_id}/cards/{id}      | Authorization : accessToken | { "status" : 204, "message": "카드 삭제 성공"}                        |                                                                                                |
| 카드 수정      | PUT    | /api/v1/boards/{board_id}/todos/{todo_id}/cards/{id}      | Authorization : accessToken | { "status" : 200, "message": "카드 수정 성공" }                       | { "card_name":"text", "card_description": "text", "card_color": "text", "deadline", "datatime" |
| 카드 이동      | PUT    | /api/v1/boards/{board_id}/todos/{todo_id}/cards/{id}/move | Authorization : accessToken | { "status" : 200, "message": "카드 이동 성공" }                       | [{"card_id": "id", "number": "number"}, {"card_id": "id", "number": "number"} ...]             |