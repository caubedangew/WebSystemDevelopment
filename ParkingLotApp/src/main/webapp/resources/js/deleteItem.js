function deleteItem(endpoint, parkinglotId, element) {
    if (confirm("Bạn chắc chắn với lựa chọn của mình?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                let item = document.getElementById(`${element}${parkinglotId}`);
                item.style.display = "none";
            } else
                alert("Không thể thực hiện được");
        });
    }
}


