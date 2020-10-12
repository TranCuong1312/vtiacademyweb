export class AboutRequest {
    id : number
    description : string
    video : string
    titleVideo : string
    descriptionVideo : string
    constructor(description : string, video : string, title_video : string, description_vid : string)
    {
        this.description = description
        this.video = video
        this.titleVideo = title_video
        this.descriptionVideo = description_vid
    }
}
