import 'dart:convert';

List<SampleApi> sampleApiFromJson(String str) =>
    List<SampleApi>.from(json.decode(str).map((x) => SampleApi.fromJson(x)));

String sampleApiToJson(List<SampleApi> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class SampleApi {
  String clubName;
  String description;
  Details details;
  String facultyCoordinator;
  String teamMember;
  String websiteUrl;
  String socialMediaUrl;
  String? image;

  SampleApi({
    required this.clubName,
    required this.description,
    required this.details,
    required this.facultyCoordinator,
    required this.teamMember,
    required this.websiteUrl,
    required this.socialMediaUrl,
    this.image,
  });

  factory SampleApi.fromJson(Map<String, dynamic> json) => SampleApi(
        clubName: json["Club name"],
        description: json["Description"],
        details: Details.fromJson(json["Details"]),
        facultyCoordinator: json["Faculty Coordinator"],
        teamMember: json["Team Member"],
        websiteUrl: json["Website Url"],
        socialMediaUrl: json["Social Media Url"],
        image: json["image"],
      );

  Map<String, dynamic> toJson() => {
        "Club name": clubName,
        "Description": description,
        "Details": details.toJson(),
        "Faculty Coordinator": facultyCoordinator,
        "Team Member": teamMember,
        "Website Url": websiteUrl,
        "Social Media Url": socialMediaUrl,
        "image": image,
      };
}

class Details {
  String location;
  String achievements;
  String technicalMagazine;

  Details({
    required this.location,
    required this.achievements,
    required this.technicalMagazine,
  });

  factory Details.fromJson(Map<String, dynamic> json) => Details(
        location: json["Location"],
        achievements: json["Achievements"],
        technicalMagazine: json["Technical Magazine"],
      );

  Map<String, dynamic> toJson() => {
        "Location": location,
        "Achievements": achievements,
        "Technical Magazine": technicalMagazine,
      };
}
