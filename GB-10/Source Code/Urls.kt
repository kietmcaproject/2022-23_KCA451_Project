package com.app.pepens.utils

/**
 * Created by Vikas Kumar Singh on 08/07/20.
 */

class Urls {

    companion object {

//        const val BASE_URL = "https://api.a2zsearch.net/api/"
        const val BASE_URL = "https://devapi.a2zsearch.net/api/"

        const val USER_ID = "&userId="
        const val LOGIN_BUSINESS_ID = "&loginBusinessId="
        const val TOKEN = "&token="
        const val AREA_ID = "&areaId="
        const val TERM = "&term="
        const val PAGE_NO = "&pageNo="
        const val PAGE_SIZE = "&pageSize="
        const val RADIUS = "&Radius="
        const val PLACEMENT = "&adPlacementId="
        const val INTERESTS = "&isOnlyUserInterest="
        const val LINKED = "&isLinked="
        const val ITEM_DETAIL_ID = "&itemDetailId="
        const val ITEM_CATEGORY_ID = "&itemCategoryId="
        const val BUSINESS_CATEGORY_ID = "&businessCategoryId="
        const val BUSINESS_ID = "&businessId="
        const val ITEM_ID = "&itemId="
        const val ITEM_IDS = "&itemIds="
        const val OFFER_TYPE = "&offerType="
        const val ORDER_BY = "&orderBy="
        const val ORDER_ID = "&orderId="
        const val STATUS_TYPE = "&statusType="
        const val TYPE = "&type="
        const val TO_ID = "&toId="
        const val SHOPPING_PARTNER_ID = "&partnerId="
        const val PARENT_ITEM_ID = "&partnerItemId="
        const val SHOPPING_PARENT_ID = "&partnerId="
        const val CUSTOMER_SECTION_ID = "&customSectionId="
        const val BUSINESS_SHOPPING_ID = "&businessShoppingItemId="
        const val IS_TREE = "&isTreeMode="
        const val TOWN_ID = "&townId="
        const val MOBILE_NO = "&mobileNo="
        const val REWARD_POINTS = "&rewardPoints="
        const val ON_OFF_STATUS = "&onOffStatus="
        const val VEG_NONVEG = "&isServingNonVeg="
        const val FAVORITE = "&isFavorite="
        const val CUISINE = "&cuisineId="
        const val ITEM_TAG_ID = "&itemTagId="
        const val PARENT_BUSINESS_CAT = "&parentBusinessCategoryId="
        const val USER_ENTITY_TYPE = "&userEntityType="
        const val ENTITY_TYPE = "&entityType="
        const val ENTITY_ID = "&entityId="
        const val IS_SHOWING_IN_HOME = "&isShowInHome="
        const val ITEMS_PER_SECTION = "&noOfItemsPerSection="

        const val AddUpdateDevice = BASE_URL + "AppDevice/AddUpdateAppDevice"
        const val REQUEST_LOGIN = BASE_URL + "user/LoginRequest"
        const val LOGIN_WITH_MOBILE = BASE_URL + "user/AppLoginWithMobileNo"
        const val GET_USER_ON_STARTUP = BASE_URL + "user/GetUserOnStartup/?userId="
        const val GET_USER = BASE_URL + "user/GetUser/?userId="
        const val GET_INTERESTS = BASE_URL + "user/GetUserInterests/?userId="
        const val UPDATE_INTERESTS = BASE_URL + "user/UpdateUserInterest"
        const val GET_FEEDS = BASE_URL + "feed/getFeeds/?UserId="
        const val GET_FEED = BASE_URL + "feed/GetFeed/?feedId="
        const val GET_CATEGORIES_FOR_HOME =
            BASE_URL + "common/GetBusinessCategoriesForUser/?UserId="
        const val GET_BUSINESSS = BASE_URL + "search/GetBusinesses/?userId="
        const val GET_FAV_BUSINESSS = BASE_URL + "user/GetUserFavoriteBusinesses/?userId="
        const val GET_All_BUSINESS = BASE_URL + "common/GetBusinessCategories/?term="
        const val GET_ALL_ITEMS = BASE_URL + "ItemCategory/GetItemCategories/?term="
        const val GET_BUSINESS = BASE_URL + "business/GetBusiness/?userId="
        const val GET_SEARCH = BASE_URL + "Search/SearchAutocomplete/?userId="
        const val ADD_FAVORITE = BASE_URL + "user/AddUpdateUserFavorite/"
        const val GET_ITEMS_FOR_BUSINESS = BASE_URL + "BusinessItem/GetBusinessItems/?businessId="
        const val GET_ITEMS_FOR_BUSINESS_WITH_CATEGORY =
            BASE_URL + "BusinessItem/GetBusinessItemsGroupByItemCategory/?businessId="
        const val GET_OFFERS = BASE_URL + "offer/GetBusinessOffers/?businessId="
        const val GET_BUSINESS_OFFERS = BASE_URL + "offer/GetUserOffers/?userId="
        const val GET_OFFER_DETAILS = BASE_URL + "offer/GetOffer/?offerId="
        const val GET_LATEST_OFFER = BASE_URL + "offer/GetUserLatestOffers/?userid="
        const val GET_GALLERY_IMAGES =
            BASE_URL + "BusinessGalleryImage/GetBusinessGalleryImages/?businessId="
        const val UPDATE_STREAM = BASE_URL + "ClickStream/InsertUserClickStream"
        const val GET_BRANDS = BASE_URL + "BusinessItem/GetBusinessItemBrands/?businessId="
        const val GET_BRANDS_LIST =
            BASE_URL + "Shopping/getPartnerItemBrands/?userId=" //&partnerId=2&pageNo=1&pageSize=500
        const val GET_NOTIFICATIONS = BASE_URL + "Notification/GetNotifications/?toType="
        const val MARK_READ = BASE_URL + "Notification/MarkAsRead"
        const val GET_ADS = BASE_URL + "ad/GetAds/?userid="
        const val ADD_WAIT_LIST = BASE_URL + "UserWaitItem/AddUpdateUserWaitItem/"
        const val ADD_INTEREST = BASE_URL + "Offer/AddUpdateUserOfferInterest"
        const val GET_WAIT_ITEMS = BASE_URL + "UserWaitItem/GetUserWaitItems/?userId="
        const val GET_TREANDING_ITEMS = BASE_URL + "Search/GetTrendingSearches/?businessId="
        const val GET_POPULAR_ITEMS = BASE_URL + "Search/PopularSearches/?userId="
        const val UPDATE_SHOPPING = BASE_URL + "user/UpdateLocation/"
        const val SET_DEFAULT_ADDRESS = BASE_URL + "address/SetDefaultAddress/"
        const val GET_SHOPPING_LISTING =
            BASE_URL + "Shopping/GetCustomSectionsWithItems/?userId=" //&partnerId=
        const val GET_SHOPPING_LISTING_PAGE =
            BASE_URL + "Shopping/GetCustomSectionItems/?userId=" //&partnerId=2&customSectionId=1
        const val GET_TODAY_OFFERS =
            BASE_URL + "Shopping/GetTodayOfferItems/?userId=" //4&partnerId=1&itemCategoryid=0&term=&pageNo=1&pageSize=50
        const val GET_DEALS_OF_WEEK =
            BASE_URL + "Shopping/GetDealsOfWeekItems/?userId=" //4&partnerId=1&itemCategoryid=0&term=&pageNo=1&pageSize=50"
        const val GET_TOP_SELLING =
            BASE_URL + "Shopping/GetTopSellingItems/?userId=" //4&partnerId=1&itemCategoryid=0&term=&pageNo=1&pageSize=50"
        const val GET_PRODUCT_DETAILS =
            BASE_URL + "Shopping/GetShoppingItemDetail/?userId=" //4&partnerId=1&partnerItemId=1
        const val GET_PRODUCT_SEARCH =
            BASE_URL + "Shopping/ShoppingAutocomplete/?userId=" //4&partnerId=1&itemCategoryid=0&term=&pageNo=1&pageSize=50"
        const val GET_AFTER_ORDER_RECOMMENDED_ITEMS =
            BASE_URL + "Shopping/GetAfterOrderRecommendedItems/?userId=" //4&partnerId=1&itemCategoryid=0&term=&pageNo=1&pageSize=50"
        const val PARENT_ITEM_CATEGORIES =
            BASE_URL + "Shopping/GetParentItemCategories/?userId="// &term="
        const val ITEM_CATEGORIES =
            BASE_URL + "Shopping/GetItemCategories/?userId=" //&isTreeMode=true&term="
        const val UPDATE_SHOPPING_CART = BASE_URL + "Shopping/AddUpdateShoppingCartItem/"
        const val GET_SHOPPING_CART =
            BASE_URL + "Shopping/GetShoppingCartItems/?userId=" //1&partnerId=// 1
        const val ADD_UPDATE_ADDRESS = BASE_URL + "Address/AddUpdateAddress/"
        const val DELETE_ADDRESS = BASE_URL + "Address/DeleteAddress/"
        const val GET_ADDRESS = BASE_URL + "Address/GetAddresses/?entityType=4&entityId="
        const val GET_ADDRESS_DETAILS = BASE_URL + "Address/GetAddress/?AddressId="
        const val GET_TOWNS = BASE_URL + "Town/GetTowns/?areaId=" //&term=
        const val GET_TOWN = BASE_URL + "Town/GetTown/?townId="
        const val CLEAR_CART = BASE_URL + "Shopping/ClearShoppingCart/?userId="
        const val PLACE_ORDER = BASE_URL + "Shopping/CreateOrder/"
        const val CANCEL_ORDER = BASE_URL + "Shopping/CancelOrder/"
        const val REPEAT_SHOPPING_ORDER = BASE_URL + "Shopping/ReOrderOrder/"
        const val GET_SHOPPING_ITEMS = BASE_URL + "Shopping/GetShoppongItems/"
        const val GET_BUSINESS_ITEMS = BASE_URL + "shopping/GetBusinessShoppongItems/"
        const val GET_ORDERS =
            BASE_URL + "Shopping/GetOrders/?userId=" //1&term=&pageNo=1&pageSize=20
        const val GET_ORDER_DETAILS = BASE_URL + "Shopping/GetOrder/?orderId="
        const val GET_MINIMUM_ORDER = BASE_URL + "Common/GetMinimumOrderAmount"
        const val GET_AVAILABLE_REWARD = BASE_URL + "user/GetCurrentRewardPoints/?userid="
        const val UPDATE_LOG = BASE_URL + "AppLog/AddUpdateAppLog"
        const val GET_REWARDS = BASE_URL + "user/getRewardPoints/?userid="
        const val GET_PIN_CODES = BASE_URL + "town/GetLocations/?term="
        const val GET_AREA_BY_GEOCORDINATES =
            BASE_URL + "area/GetAreaByGeoCoordinate?lat="//28.7352996&long=75.6126327
        const val GET_AVAILABLE_LOCATIONS =
            BASE_URL + "Area/GetAvailableAreas/?term=&pageNo=1&pageSize=0"
        const val ADD_TO_WISHLIST = BASE_URL + "Shopping/AddShoppingWishlist"
        const val GET_SLOTS = BASE_URL + "DeliverySlot/GetDeliverySlots/?areaId="
        const val GET_RESTAURANTS =
            BASE_URL + "Shopping/GetRestaurants/?userId=" //&partnerId=2&businessCategoryId=71&pageNo=1&pageSize=5
        const val GET_RESTAURANT_TRENDING_ITEMS =
            BASE_URL + "Shopping/GetRestaurantTrendingItems/?userId=" //&partnerId=2&businessId=0&itemCategoryId=0&pageNo=1&pageSize=5
        const val GET_RESTAURANT_ITEMS =
            BASE_URL + "Shopping/GetRestaurantItems/?userId=" //&partnerId=2&businessId=0&itemCategoryId=0&pageNo=1&pageSize=5
        const val GET_RESTAURANT_ITEM_DETAILS =
            BASE_URL + "Shopping/GetRestaurantItemDetail/?userId=" //5&partnerId=2&businessId=34&businessShoppingItemId=367
        const val GET_REGIONS =
            BASE_URL + "Shopping/GetCuisines/?userId=" //&partnerId=2&businessCategoryId=71&pageNo=1&pageSize=5
        const val GET_TRENDING_REGIONS =
            BASE_URL + "Shopping/GetTrendingCuisines/?userId=" //&partnerId=2&pageNo=1&pageSize=5
        const val GET_RESTAURANT_DETAILS =
            BASE_URL + "Shopping/GetRestaurant/?userId=" //&partnerId=2&businessId=34
        const val AFTER_PAYMENT = BASE_URL + "Shopping/UpdateOrderAfterPayment/"
        const val UPDATE_SIGNATURE = BASE_URL + "Shopping/UpdateOrderPaymentGatewaySignature/"
        const val GET_SHOPPING_ORDER = BASE_URL + "Shopping/GetOrderStatus/?OrderId="
        const val APPLY_OFFER = BASE_URL + "Coupon/apply"
        const val GET_COUPONS = BASE_URL + "Coupon/GetCouponsForUser"
        const val GET_ALL_COUPONS = BASE_URL + "Coupon/GetAvailableCoupons"
        const val GET_PLACED_ORDER_DETAILS = BASE_URL + "Shopping/GetOrder/?userId="
        const val CANCEL_ORDER_REASONS =
            BASE_URL + "Common/GetOrderReasons/?userEntityType=4&status=6"
        const val DELIVERY_CHARGE = BASE_URL + "Common/CalculateDeliveryCharge"
        const val PARTNER_DETAILS = BASE_URL + "Partner/GetPartner/?partnerId="
        const val TRANSFER_POINTS = BASE_URL + "user/TransferRewardPoints/?userId="
        const val GET_TOP_CUISINES =
            BASE_URL + "shopping/GetTopCuisines/?userId=" //&areaId=1&pageNo=1&pageSize=20"
        const val GET_TOP_ITEMS =
            BASE_URL + "shopping/GetRestaurantTopItems/?userId=" //&areaId=1&pageNo=1&pageSize=20"
        const val GET_TOP_RESTAURANTS =
            BASE_URL + "shopping/GetTopRestaurants/?userId=" //&areaId=1&term=&pageNo=1&pageSize=20"
        const val GET_TRENDING_RESTAURANTS =
            BASE_URL + "shopping/GetTrendingRestaurants/?userId=" //&areaId=1&term=&pageNo=1&pageSize=20"

        const val GET_HOME_CATEGORIES =
            BASE_URL + "BusinessCategoryArea/GetBusinessCategoriesForAppHome/?areaId=" //&userId=
        const val GET_PARENT_CATEGORIES =
            BASE_URL + "BusinessCategoryArea/GetParentBusinessCategories/?areaId=" //&userId=
        const val GET_CHILD_CATEGORIES =
            BASE_URL + "BusinessCategoryArea/GetBusinessCategories/?areaId=" //&userId= &parentBusinessCategoryId=

        const val GET_SIMILAR_ITEMS = BASE_URL + "Shopping/GetSimilarItems/?userId="
        const val GET_RECOMMENDED_ITEMS = BASE_URL + "Shopping/GetRecommendedItems/?userId="

        const val GET_PICK_DROP_CATEGORIES = BASE_URL + "Common/GetPickupDropCategories/?areaId="
        const val CALCULATE_CHARGES = BASE_URL + "Common/CalculatePickupDropCharge/"
        const val GET_PICK_DROP_ADDRESS = BASE_URL + "Address/GetAddress/?addressId="
        const val ADD_UPDATE_APP_LOG = BASE_URL + "AppLog/AddUpdateAppLog"
        const val UPDATE_RATING = BASE_URL + "Review/AddUpdateReview"
        const val GET_RATING =
            BASE_URL + "Review/GetReviewsByEntity/?userEntityType=4&userEntityId="
        const val FETCH_DELIVERY_LOCATION =
            BASE_URL + "DeliveryPartner/GetOrderLiveLocation/?deliveryPartneId="

        const val BUSINESS_PRODUCT_CATEGORIES =
            BASE_URL + "Shopping/GetBusinessItemCategories/?userId=" //&isTreeMode=true&term="

        const val NOTIFICATION_ITEM = 2
        const val NOTIFICATION_OFFER = 9
        const val NOTIFICATION_FEED = 4
        const val NOTIFICATION_WELCOME = 12 //  SHOPPING SECTION
        const val NOTIFICATION_REWARD = 13 // Me-REWARD POINTS
        const val NOTIFICATION_REFRAL_REWARD = 14 // Another-REWARD POINTS
        const val NOTIFICATION__REWARD = 16 // REWARD POINTS
        const val NOTIFICATION___REWARD = 18 // REWARD POINTS
        const val NOTIFICATION_ORDER_CONFIRM = 17 // ORDER DETAILS
        const val NOTIFICATION_ORDER_DELIVER = 17 // ORDER DETAILS
        const val NOTIFICATION_SHOPPING_CAT = 20 // DYNAMIC CATEGORY
        const val NOTIFICATION_CUSTOM = 21 // DYNAMIC CATEGORY
        const val NOTIFICATION_POINTS = 22
        const val NOTIFICATION_BUSINESS = 27

        const val ID_RESTAURANT_CATEGORY = 71
        const val ID_GROCERY_CATEGORY = 104
        const val ID_PICK_DROP_CATEGORY = 123

        const val OPEN_CLOSE_TIME = 30

        const val MINIMUM_ACCURACY = 50

        const val HOME_PAGE = 1
        const val RESTAURANT_PAGE = 2
        const val RESTAURANT_DETAILS_PAGE = 3
        const val RESTAURANT_SEARCH_PAGE = 4
        const val RESTAURANT_PLACE_ORDER_PAGE = 5
        const val PICK_AND_DROP_PAGE = 6
        const val SHOPPING_PAGE = 7
        const val SHOPPING_ALL_PRODUCTS_PAGE = 8
        const val SHOPPING_PRODUCT_DETAILS_PAGE = 9
        const val SHOPPING_ALL_CATEGORIES_PAGE = 10
        const val SHOPPING_LISTING_PAGE = 11
        const val SHOPPING_SHOP_BY_CATEGORIES_PAGE = 12
        const val SHOPPING_SEARCH_PAGE = 13
        const val SHOPPING_PLACE_ORDER_PAGE = 14
        const val PAYMENTS_PAGE = 15
        const val ORDER_SUCCESS_PAGE = 16
        const val ORDERS_PAGE = 18
        const val ORDER_DETAILS_PAGE = 19
        const val SEARCH_PAGE = 20
        const val ALL_CATEGORIES_PAGE = 21
        const val BUSINESS_LISTING_PAGE = 22
        const val BUSINESS_DETAILS_PAGE = 23
        const val OFFERS_PAGE = 24
        const val OFFERS_DETAILS_PAGE = 25
        const val FEED_PAGE = 26
        const val FEED_DETAILS_PAGE = 27
        const val NOTIFICATIONS_PAGE = 28
        const val FAVORITE_BUSINESS_PAGE = 29
        const val ADDRESS_LIST_PAGE = 30
        const val ADD_UPDATE_ADDRESS_PAGE = 31
        const val LOCATION_PAGE = 32
        const val REWARDS_PAGE = 33
        const val REFER_EARN_PAGE = 34
        const val SHOPPING_PRODUCT_VIA_TYPE = 35
        const val STARTUP_LOCATION_PAGE = 36
        const val SPLASH_PAGE = 37
        const val LOGIN_PAGE = 38
        const val OTP_PAGE = 39
        const val STARTUP_PROFILE_PAGE = 40
        const val UPDATE_PROFILE_PAGE = 41
    }

}