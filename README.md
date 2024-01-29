<h1 class="code-line" data-line-start=0 data-line-end=1 ><a id="Spring___0"></a>Spring 파일 업로드</h1>
<h2 class="code-line" data-line-start=3 data-line-end=4 ><a id="_3"></a>최종결과</h2>
<p class="has-line-data" data-line-start="4" data-line-end="5">상품등록 화면</p>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/c9d9e337-e505-4bdb-8126-5aacfa9d551a"></img>
<ul>
<li class="has-line-data" data-line-start="6" data-line-end="7">상품을 등록할 때는, 첨부파일 하나와, 여러 장의 이미지 파일을 첨부할 수 있습니다.</li>
<li class="has-line-data" data-line-start="7" data-line-end="9">서버로 multipart-form-data 형식으로 데이터를 전송합니다.</li>
</ul>
<p class="has-line-data" data-line-start="9" data-line-end="10">상품조회 화면</p>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/70e2e995-14db-44a0-b956-81b532014ab3"></img>

<p class="has-line-data" data-line-start="12" data-line-end="13">DB에 저장된 결과</p>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/ff4f79e4-1956-492e-8bfb-a70ad896a3ab"></img>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/9b2fd11d-9c2f-4c2d-93d1-ead951a0faab"></img>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/68d934b9-882d-4c93-84dd-008cf26a7b6f"></img>

<ul>
<li class="has-line-data" data-line-start="14" data-line-end="15">DB에는 파일의 바이너리 데이터를 직접 저장하는 대신, 클라이언트가 정의한 파일명(ex.hello.jpg), 서버에서 uuid로 정한 파일명(ex.qwe-qwe-qwe123.jpg) 두 가지 값만을 저장합니다.</li>
<li class="has-line-data" data-line-start="15" data-line-end="17">이후 파일의 바이너리 데이터를 조회할 때는, &quot;(기본파일저장경로)/(서버에서 정한 uuid파일명)&quot;의 경로로 접근하여 데이터를 꺼냅니다.</li>
</ul>
<p class="has-line-data" data-line-start="17" data-line-end="18">파일에 저장된 결과</p>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/a7a8c6e0-306b-48cc-8efd-658d035007b7"></img>
<h2 class="code-line" data-line-start=20 data-line-end=21 ><a id="__20"></a>엔티티 다이어그램</h2>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/49b675d5-0dc5-4c4f-b5a8-bd78457b4e11"></img>
<p class="has-line-data" data-line-start="21" data-line-end="22">3가지 엔티티 Item, InfoFile, ImageFile를 가집니다.</p>
<ul>
<li class="has-line-data" data-line-start="23" data-line-end="24">Item Entity는 DB에 아이디, 상품명의 정보를 저장합니다. 그리고 첨부파일의 정보를 담은 InfoFile Entity와 일대일 관계를 맺고, 이미지파일의 정보를 담은 ImageFile Entity와는 일대다 관계를 가집니다.</li>
<li class="has-line-data" data-line-start="24" data-line-end="25">InfoFile Entity는 클라이언트가 정의한 파일명(ex.hello.jpg) 그리고 서버에 저장할 때 사용하는 서버저장파일명(ex.qwe-qwe-qwe123.jpg)를 저장합니다. 이후 이 값을 사용해서 해당 파일을 조회할 수 있습니다.</li>
<li class="has-line-data" data-line-start="25" data-line-end="26">ImageFile Entity도 클라이언트가 정의한 파일명과 서버에 저장할 때 사용하는 서버저장파일명을 필드변수로 가집니다. 역시 이 값들을 사용해서 이미지를 조회할 수 있습니다.</li>
</ul>
<h2 class="code-line" data-line-start=28 data-line-end=29 ><a id="__28"></a>작동 흐름</h2>
<p class="has-line-data" data-line-start="30" data-line-end="31">첨부파일(ItemInfo) 및 이미지(ItemImage) 저장 흐름도</p>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/84066170-540d-4ddb-b4b7-6e697f7c7ed1"></img>
<ul>
<li class="has-line-data" data-line-start="32" data-line-end="33">[1] 클라이언트가 Html폼에서 Post방식으로 첨부파일 및 이미지를 서버로 전송합니다. 서버의 ItemController가 @ModelAttribute로 데이터를 받습니다.</li>
<li class="has-line-data" data-line-start="33" data-line-end="34">[2] ItemController가 ItemService로 데이터를 전달합니다.</li>
<li class="has-line-data" data-line-start="34" data-line-end="35">[3, 4, 5] ItemService가 첨부파일, 이미지파일에 대한 uuid 서버저장 파일명을 생성하고, 디스크에 저장합니다. 그리고 ItemService로 클라이언트 정의 파일명과 서버저장파일명, 두 개의 값을 전달합니다.</li>
<li class="has-line-data" data-line-start="35" data-line-end="37">[6, 7] ItemService는 받은 클라이언트 정의 파일명, 서버저장파일명을 DB에 저장합니다.</li>
</ul>
<p class="has-line-data" data-line-start="37" data-line-end="38">첨부파일(ItemInfo) 조회</p>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/a96f5e5f-34db-49d2-a002-e7742a8b70a6"></img>
<ul>
<li class="has-line-data" data-line-start="39" data-line-end="40">[1] 클라이언트가 서버로 /attach/{itemId}경로로 첨부파일을 요청합니다.</li>
<li class="has-line-data" data-line-start="40" data-line-end="41">[2, 3, 4, 5, 6] 서버는 DB에서 해당 itemId를 가지는 Item을 가져옵니다.</li>
<li class="has-line-data" data-line-start="41" data-line-end="42">[7] InfoController는 DB에서 가져온 Item의 클라이언트 파일명, 서버 파일명을 사용하여 디스크로부터 파일들을 가져옵니다.</li>
<li class="has-line-data" data-line-start="42" data-line-end="44">[8, 9] 가져온 파일들을 UTF_8로 인코딩하고, ContentDisPosition을 세팅해서 클라이언트에게 보냅니다.</li>
</ul>
<p class="has-line-data" data-line-start="44" data-line-end="45">이미지(ItemImage) 조회</p>
<img src="https://github.com/DoubleDRG/Spring-File-Upload-Download/assets/147648419/d6a7bd77-620d-44aa-ab7f-cfd580874ba7"></img>
<ul>
<li class="has-line-data" data-line-start="46" data-line-end="47">[1] 클라이언트가 서버로 /images/{서버저장파일명}의 경로로 이미지파일을 요청합니다.</li>
<li class="has-line-data" data-line-start="47" data-line-end="48">[2, 3, 4] 서버는 디스크의 서버저장파일명의 주소를 사용해서 이미지파일을 가져오고, 이를 클라이언트에게 보냅니다.</li>
</ul>
