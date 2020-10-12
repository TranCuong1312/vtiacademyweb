export class Review{
    id: number;
    image: string;
    content: string;
    reviewerName: string;
    office: string;
    type: string;

    constructor (image, content, reviewerName, office, type){
        this.image = image;
        this.content = content;
        this.reviewerName = reviewerName;
        this.office = office;
        this.type = type;
    }
}
