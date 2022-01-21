import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook, faTwitter, faWhatsapp } from "@fortawesome/free-brands-svg-icons"
import { Button, Modal } from "react-bootstrap";

const Share = (props: any) => {
    return (
        <Modal show={props.show}
            onHide={props.onHide}
            aria-labelledby="shareModal"
            centered>
            <Modal.Header closeButton>
                <Modal.Title id="shareModal">
                    Share Dobble with your friends!
                </Modal.Title>
            </Modal.Header>
            <Modal.Body style={{ textAlign: "center" }}>
                <p>Copy the link</p>
                <input type="text" value="http://localhost:3000/" style={{ width: "100%", marginBottom: "0.5rem" }} readOnly />

                <div className="fb-share-button" data-href="http://localhost:3000/" data-layout="button" data-size="small"><a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A3000%2F&amp;src=sdkpreparse" className="fb-xfbml-parse-ignore">Share <FontAwesomeIcon icon={faFacebook} /> </a></div>

                <div><a href="https://twitter.com/intent/tweet?url=http%3A%2F%2Flocalhost%3A3000%2F">Share  <FontAwesomeIcon icon={faTwitter} /></a></div>

                <div><a href="whatsapp://send?text=http://localhost:3000/">Share <FontAwesomeIcon icon={faWhatsapp} /></a></div>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={props.onHide}>Close</Button>
            </Modal.Footer>
        </Modal>
    )
}

export default Share;